package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.sakaiproject.authz.api.Member;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.email.api.EmailService;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.section.api.SectionAwareness;
import org.sakaiproject.section.api.SectionManager;
import org.sakaiproject.section.api.coursemanagement.CourseSection;
import org.sakaiproject.section.api.coursemanagement.EnrollmentRecord;
import org.sakaiproject.service.gradebook.shared.AssessmentNotFoundException;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.GradeDefinition;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.tool.gradebook.Gradebook;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserNotDefinedException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.dao.NWUCourseLessonDao;
import za.ac.nwu.api.dao.NWULessonGradeDao;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBLesson;
import za.ac.nwu.api.model.NWULessonGrade;

/**
 * This class manages Lessons in Gradebook for courses.
 * 
 * @author JC Gillman
 * 
 */
@Slf4j
public class NWUCourseLessonPlanManager {

	private UserDirectoryService userDirectoryService;

	private ServerConfigurationService serverConfigurationService;

	private SiteService siteService;

	private GradebookService gradebookService;

	private SectionManager sectionManager;

	private SecurityService securityService;

	private EmailService emailService;

	public NWUCourseLessonPlanManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService,
			final GradebookService gradebookService, final SectionManager sectionManager,
			SecurityService securityService, EmailService emailService) {

		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;
		this.gradebookService = gradebookService;
		this.sectionManager = sectionManager;
		this.securityService = securityService;
		this.emailService = emailService;
	}

	/**
	 * Create/Update Gradebook item
	 * 
	 * @param lessonDao
	 * @param course
	 * @param previousFireTime
	 */
	public void updateCourseLessonPlan(NWUCourseLessonDao lessonDao, NWUCourse course, Date previousFireTime,
			String currentUserId) {

		for (NWUGBLesson lesson : course.getLessons()) {

//			if (previousFireTime != null && !lesson.getAuditDateTime().isAfter(previousFireTime.toInstant())) {
//				continue;
//			}
			
			try {
				Site site = siteService.getSite(course.getEfundiSiteId());
			} catch (IdUnusedException e1) {
				log.info("Site not found for course : " + course);
				continue;
			}

			if (lesson.getAction() != null && lesson.getAction().equalsIgnoreCase(Constants.CREATE)) {
				createAssignment(lessonDao, course, lesson, Constants.CREATE);
			} else if (lesson.getAction() != null && lesson.getAction().equalsIgnoreCase(Constants.UPDATE)) {
				updateAssignment(lessonDao, course, lesson, Constants.UPDATE, currentUserId);
			} else if (lesson.getAction() != null && lesson.getAction().equalsIgnoreCase(Constants.DELETE)) {
				deleteAssignment(lessonDao, course, lesson, Constants.DELETE, currentUserId);
			}
		}
	}

	/**
	 * Creates a new Assignment
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lesson
	 * @param action
	 */
	private void createAssignment(NWUCourseLessonDao lessonDao, NWUCourse course, NWUGBLesson lesson, String action) {		

		try {

			if (lesson.getEfundiGradebookId() == null && lesson.getProcessed() == 0) {

				createNewAssignment(lessonDao, course, lesson, action);

			} else {
				Assignment assignment = gradebookService.getAssignmentByIDEvenIfRemoved(lesson.getEfundiGradebookId());

				if (assignment.isRemoved()) {
					gradebookService.restoreAssignment(assignment.getId());
				}

				String assignmentName = getAssignmentName(lesson);

				if (!assignmentName.equals(assignment.getName())) {
					assignment.setName(assignmentName);
					gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
				}

				if (!lesson.getClassTestMaxScore().equals(assignment.getPoints())) {
					assignment.setPoints(lesson.getClassTestMaxScore());
					gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
				}
				if (lesson.getProcessed() == 0) {					
					lesson.setProcessed((byte) 1);
					lesson.setControlNote(action);
					lesson.setAuditDateTime(Instant.now());
					lessonDao.updateLesson(lesson);
					log.info("Gradebook item updated for Lesson plan: " + lesson);
				}
			}

		} catch (AssessmentNotFoundException anf) {

			// If a user deleted a Gradebook item but the lesson plan table has already been
			// updated with the efundiGradebookId, a new one must be created

			createNewAssignment(lessonDao, course, lesson, action);

		} catch (ConflictingAssignmentNameException cane) {
			// Don't know why this can happen. Transaction related, perhaps. Not
			// that important, anyway.
			log.warn("Failed to set gb item name to: ", getAssignmentName(lesson));
		}		
	}

	/**
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lesson
	 * @param action
	 */
	private void createNewAssignment(NWUCourseLessonDao lessonDao, NWUCourse course, NWUGBLesson lesson,
			String action) {
		Assignment assignment = new Assignment();
		assignment.setName(getAssignmentName(lesson));// SP-classTestName-(lessonCode-classTestCode)-classTestMaxScore
		assignment.setPoints(lesson.getClassTestMaxScore());
		assignment.setReleased(false);
		Long assignmentId = gradebookService.addAssignment(course.getEfundiSiteId(), assignment);
		lesson.setEfundiGradebookId(assignmentId);
		lesson.setProcessed((byte) 1);
		lesson.setControlNote(action);
		lesson.setAuditDateTime(Instant.now());
		lessonDao.updateLesson(lesson);
		log.info("Gradebook item created for Lesson plan: " + lesson);
	}

	/**
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lesson
	 * @param action
	 * @param currentUserId
	 */
	private void updateAssignment(NWUCourseLessonDao lessonDao, NWUCourse course, NWUGBLesson lesson, String action,
			String currentUserId) {

		try {
			Assignment assignment = gradebookService.getAssignmentByIDEvenIfRemoved(lesson.getEfundiGradebookId());
			
			if (assignment.isRemoved()) {
				gradebookService.restoreAssignment(assignment.getId());
			}

			String assignmentName = getAssignmentName(lesson);

			if (!assignmentName.equals(assignment.getName())) {
				assignment.setName(assignmentName);
				gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
			}

			if (!lesson.getClassTestMaxScore().equals(assignment.getPoints())) {
				assignment.setPoints(lesson.getClassTestMaxScore());
				gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
			}
			if (lesson.getProcessed() == 0) {				

				Site site = siteService.getSite(course.getEfundiSiteId());
				Set<String> students = site.getUsersIsAllowed(SectionAwareness.STUDENT_MARKER);

				List<GradeDefinition> grades = gradebookService.getGradesForStudentsForItem(site.getId(),
						assignment.getId(), new ArrayList<String>(students));

				if (grades.isEmpty()) {
					lesson.setControlNote(action);
				} else {
					lesson.setControlNote(Constants.ERROR);
					sendEmailToInstructors(site, currentUserId);
				}

				lesson.setProcessed((byte) 1);
				lesson.setAuditDateTime(Instant.now());
				lessonDao.updateLesson(lesson);
				log.info("Gradebook item updated for Lesson plan: " + lesson);
			}

		} catch (AssessmentNotFoundException anf) {

			// If a user deleted a Gradebook item but the lesson plan table has already been
			// updated with the efundiGradebookId, a new one must be created

			createNewAssignment(lessonDao, course, lesson, Constants.UPDATE);

		} catch (IdUnusedException e) {

			lesson.setControlNote(action);
			lesson.setProcessed((byte) 1);
			lesson.setAuditDateTime(Instant.now());
			lessonDao.updateLesson(lesson);
			log.info("Gradebook item updated for Lesson plan: " + lesson);
		}
		log.info("Lesson Plan: " + lesson + "; Action: " + action);
	}

	/**
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lesson
	 * @param action
	 * @param currentUserId
	 */
	private void deleteAssignment(NWUCourseLessonDao lessonDao, NWUCourse course, NWUGBLesson lesson, String action,
			String currentUserId) {

		try {
			Assignment assignment = gradebookService.getAssignmentByIDEvenIfRemoved(lesson.getEfundiGradebookId());

			String assignmentName = getAssignmentName(lesson);

			if (!assignmentName.equals(assignment.getName())) {
				assignment.setName(assignmentName);
				gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
			}

			if (!lesson.getClassTestMaxScore().equals(assignment.getPoints())) {
				assignment.setPoints(lesson.getClassTestMaxScore());
				gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
			}
			
			if (lesson.getProcessed() == 0) {

				Site site = siteService.getSite(course.getEfundiSiteId());
				Set<String> students = site.getUsersIsAllowed(SectionAwareness.STUDENT_MARKER);

				List<GradeDefinition> grades = gradebookService.getGradesForStudentsForItem(site.getId(),
						assignment.getId(), new ArrayList<String>(students));

				if (grades.isEmpty()) {
					lesson.setControlNote(action);
				} else {
					lesson.setControlNote(Constants.ERROR);
					sendEmailToInstructors(site, currentUserId);
				}

				lesson.setEfundiGradebookId(null);
				lesson.setProcessed((byte) 1);
				lesson.setAuditDateTime(Instant.now());
				lessonDao.updateLesson(lesson);

				gradebookService.removeAssignment(assignment.getId());
				log.info("Gradebook item removed for Lesson plan: " + lesson);
			}

		} catch (AssessmentNotFoundException anf) {

			// If a user deleted a Gradebook item but the lesson plan table has already been
			// updated with the efundiGradebookId, a new one must be created

			lesson.setControlNote(action);
			lesson.setEfundiGradebookId(null);
			lesson.setProcessed((byte) 1);
			lesson.setAuditDateTime(Instant.now());
			lessonDao.updateLesson(lesson);
			log.info("Gradebook item removed for Lesson plan: " + lesson);

		} catch (IdUnusedException e) {

			lesson.setControlNote(action);
			lesson.setEfundiGradebookId(null);
			lesson.setProcessed((byte) 1);
			lesson.setAuditDateTime(Instant.now());
			lessonDao.updateLesson(lesson);
			log.info("Gradebook item removed for Lesson plan: " + lesson);
		}
		log.info("Lesson Plan: " + lesson + "; Action: " + action);
	}

	/**
	 * Returns Assignment Name
	 * 
	 * @param lesson
	 * @return
	 */
	private String getAssignmentName(NWUGBLesson lesson) {
		return "SP-" + lesson.getClassTestName() + "-(" + lesson.getLessonCode() + "-" + lesson.getClassTestCode()
				+ ")-" + lesson.getClassTestMaxScore().intValue();
	}

	/**
	 * Add/update Student grade
	 * 
	 * @param lessonGradeDao
	 * @param course
	 * @param previousFireTime
	 */
	public void updateLessonPlanStudentGrades(NWULessonGradeDao lessonGradeDao, NWUCourse course,
			Date previousFireTime) {

		Gradebook gradebook = (Gradebook) gradebookService.getGradebook(course.getEfundiSiteId());
		List<Assignment> assignments = gradebookService.getAssignments(gradebook.getUid());
		List<CourseSection> courseSections = sectionManager.getSections(course.getEfundiSiteId());

		String lessonId = null;
		List<EnrollmentRecord> sectionEnrollmentList = null;
		for (Assignment assignment : assignments) {

			lessonId = getLessonId(course, assignment.getId());
			if (lessonId == null) {
				log.error("Could not find lessonId for GradebookId " + assignment.getId() + " : " + course);
				continue;
			}
			List<GradeDefinition> gradesForStudentsList = null;
			for (CourseSection courseSection : courseSections) {

				sectionEnrollmentList = sectionManager.getSectionEnrollments(courseSection.getUuid());

				log.info("Section Enrollment list for SiteId = " + course.getEfundiSiteId() + "; SectionTitle = "
						+ courseSection.getTitle() + "; StudentUserIds = " + getStudentUserIds(sectionEnrollmentList));

				if (sectionEnrollmentList != null && !sectionEnrollmentList.isEmpty()) {

					gradesForStudentsList = gradebookService.getGradesForStudentsForItem(gradebook.getUid(),
							assignment.getId(), getStudentUuidsList(sectionEnrollmentList));

					int gradesCount = gradesForStudentsList == null ? 0 : gradesForStudentsList.size();
					log.info("Student grades list: gradebook.getUid() = " + gradebook.getUid()
							+ "; assignment.getId() = " + assignment.getId() + "; found " + gradesCount + " grades");
					if (gradesForStudentsList == null || gradesForStudentsList.isEmpty()) {
						continue;
					}
					updateGrades(lessonGradeDao, course, lessonId, courseSection, gradesForStudentsList,
							assignment.getPoints());
				}
			}
		}
	}

	/**
	 * 
	 * @param lessonGradeDao
	 * @param course
	 * @param lessonId
	 * @param courseSection
	 * @param gradesForStudentsList
	 * @param maxGrade
	 */
	private void updateGrades(NWULessonGradeDao lessonGradeDao, NWUCourse course, String lessonId,
			CourseSection courseSection, List<GradeDefinition> gradesForStudentsList, Double maxGrade) {
		List<NWULessonGrade> lessonGrades = lessonGradeDao.getAllGradesByLessonId(lessonId);
		NWULessonGrade lessonGrade = null;
		String nwuNumber = null;
		Double studentGrade = null;
		if (lessonGrades.isEmpty() && !gradesForStudentsList.isEmpty()) {
			log.info("No Grades found for LessonId: " + lessonId);

			for (GradeDefinition gradeDefinition : gradesForStudentsList) {

				nwuNumber = getValidStudentEid(gradeDefinition.getStudentUid());
				if (nwuNumber == null) {
					log.error("Could not find user " + gradeDefinition.getStudentUid() + ": " + course);
					continue;
				}

				studentGrade = Double.parseDouble(gradeDefinition.getGrade());
				if (Double.compare(studentGrade, maxGrade) > 0) {
					log.error("Student " + nwuNumber + " is not allowed to have a grade value " + studentGrade
							+ " that is more than the Assignment maximum grade value " + maxGrade + " : " + course);
					continue;
				}

				lessonGrade = new NWULessonGrade();
				lessonGrade.setLessonId(lessonId);
				lessonGrade.setSectionCode(StringUtils.substringBetween(courseSection.getTitle(), "(", ")"));
				lessonGrade.setNwuNumber(Integer.parseInt(nwuNumber));
				lessonGrade.setGrade(studentGrade);
				lessonGrade.setAuditDateTime(Instant.now());
				lessonGradeDao.updateLessonGrade(lessonGrade);

				log.info("Grade recorded: " + lessonGrade);
			}
		} else {

			for (GradeDefinition gradeDefinition : gradesForStudentsList) {

				nwuNumber = getValidStudentEid(gradeDefinition.getStudentUid());
				if (nwuNumber == null) {
					log.error("Could not find user " + gradeDefinition.getStudentUid() + ": " + course);
					continue;
				}

				studentGrade = Double.parseDouble(gradeDefinition.getGrade());
				if (Double.compare(studentGrade, maxGrade) > 0) {
					log.error("Student " + nwuNumber + " is not allowed to have a grade value " + studentGrade
							+ " that is more than the Assignment maximum grade value " + maxGrade + " : " + course);
					continue;
				}

				lessonGrade = getLessonGradeForStudent(lessonGrades, nwuNumber);
				if (lessonGrade == null) {

					lessonGrade = new NWULessonGrade();
					lessonGrade.setLessonId(lessonId);
					lessonGrade.setSectionCode(StringUtils.substringBetween(courseSection.getTitle(), "(", ")"));
					lessonGrade.setNwuNumber(Integer.parseInt(nwuNumber));
					lessonGrade.setGrade(studentGrade);
					lessonGrade.setAuditDateTime(Instant.now());
					lessonGradeDao.updateLessonGrade(lessonGrade);

					log.info("Grade recorded: " + lessonGrade);

				} else if (!lessonGrade.getGrade().equals(studentGrade)) {

					lessonGrade.setGrade(studentGrade);
					lessonGrade.setAuditDateTime(Instant.now());
					lessonGradeDao.updateLessonGrade(lessonGrade);

					log.info("Grade updated: " + lessonGrade);
				}
			}
		}
	}

	/**
	 * Returns the NWULessonGrade for the studentUid
	 * 
	 * @param lessonGrades
	 * @param studentUid
	 * @return
	 */
	private NWULessonGrade getLessonGradeForStudent(List<NWULessonGrade> lessonGrades, String nwuNumber) {

		for (NWULessonGrade lessonGrade : lessonGrades) {
			if (nwuNumber.equals(Integer.toString(lessonGrade.getNwuNumber()))) {
				return lessonGrade;
			}
		}
		return null;
	}

	/**
	 * Returns the lessonId
	 * 
	 * @param course
	 * @param id
	 * @return
	 */
	private String getLessonId(NWUCourse course, Long id) {
		List<NWUGBLesson> lessons = course.getLessons();
		for (NWUGBLesson lesson : lessons) {
			if (lesson.getEfundiGradebookId() != null && lesson.getEfundiGradebookId().equals(id)) {
				return lesson.getSourceSystemId();
			}
		}
		return null;
	}

	/**
	 * Returns a valid user Eid
	 * 
	 * @param userId
	 * @return
	 */
	private String getValidStudentEid(String userId) {
		try {
			return userDirectoryService.getUserEid(userId);
		} catch (UserNotDefinedException e) {
			log.error("User with userId " + userId + " not found: " + e.toString());
		}
		return null;
	}

	/**
	 * Returns a list of student numbers
	 * 
	 * @param enrollments
	 * @return
	 */
	private List<String> getStudentUuidsList(List<EnrollmentRecord> enrollments) {

		List<String> studentNumberList = new ArrayList<String>();
		for (EnrollmentRecord enrollment : enrollments) {
			studentNumberList.add(enrollment.getUser().getUserUid());
		}
		return studentNumberList;
	}

	/**
	 * Return a String with with all enrollment Ids
	 * 
	 * @param enrollments
	 * @return
	 */
	private String getStudentUserIds(List<EnrollmentRecord> enrollments) {

		List<String> studentNumberList = new ArrayList<String>();
		for (EnrollmentRecord enrollment : enrollments) {
			studentNumberList.add(enrollment.getUser().getDisplayId());
		}

		return String.join(",", studentNumberList);
	}

	/**
	 * 
	 * @param site
	 * @param currentUserId
	 */
	private void sendEmailToInstructors(Site site, String currentUserId) {

		Set<String> userIds = site.getUsersIsAllowed("section.role.instructor"); // INST_ROLE

		// the site could contain references to deleted users
		List<User> activeUsers = userDirectoryService.getUsers(userIds);

		for (int i = 0; i < activeUsers.size(); i++) {
			User user = activeUsers.get(i);
			if (user.getId().equals(currentUserId)) {
				continue;
			}
			String from = serverConfigurationService.getString("setup.request",
					"no-reply@" + serverConfigurationService.getServerName());
			List<String> headers = new ArrayList<>();
			String subject = "Site Lesson Plan update";
			headers.add("Subject: " + subject);
			headers.add("From: " + "\"" + serverConfigurationService.getString("ui.service", "Sakai") + "\" <" + from
					+ ">");

			emailService.send(from, user.getEmail(), subject, getEmailBodyForUser(site, user), null, null, headers);
		}
	}

	/**
	 * 
	 * @param site
	 * @param user
	 * @return
	 */
	private String getEmailBodyForUser(Site site, User user) {
		StringBuffer buf = new StringBuffer();
		buf.setLength(0);

		// email body
		buf.append("Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n");
		buf.append("There have been some changes to your course site (" + serverConfigurationService.getServerUrl()
				+ serverConfigurationService.getString("portalPath") + site.getReference() + ") on eFundi.\n\n");
		buf.append("Please review all marks to ensure they are correct, and make any necessary adjustments.\n\n");
		buf.append("Thank you,\n");
		buf.append("The eFundi Team\n");
		return buf.toString();
	}

	/**
	 * 
	 * @param siteId
	 * @return
	 */
	public List<User> getInstructorsForSite(Site site) {
		List<User> instructors = new ArrayList<User>();
		if (site != null) {
			for (Member member : site.getMembers()) {
				if (isUserInstructor(member.getUserId(), site)) {
					try {
						instructors.add(userDirectoryService.getUser(member.getUserId()));
					} catch (UserNotDefinedException e) {
						log.error(e.getMessage(), e);
					}
				}
			}
		}
		return instructors;
	}

	/**
	 * 
	 * @param userId
	 * @param site
	 * @return
	 */
	private boolean isUserInstructor(String userId, Site site) {
		if (site.getMember(userId) != null) {
			if (securityService.unlock(userId, SiteService.SECURE_UPDATE_SITE,
					siteService.siteReference(site.getId()))) {
				return true;
			}
		}
		return false;
	}
}
