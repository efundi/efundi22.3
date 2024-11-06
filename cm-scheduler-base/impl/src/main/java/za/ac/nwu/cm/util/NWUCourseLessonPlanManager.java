package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.section.api.SectionManager;
import org.sakaiproject.section.api.coursemanagement.EnrollmentRecord;
import org.sakaiproject.service.gradebook.shared.AssessmentNotFoundException;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.GradeDefinition;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.tool.gradebook.Gradebook;
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

	public NWUCourseLessonPlanManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService,
			final GradebookService gradebookService, final SectionManager sectionManager) {

		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;
		this.gradebookService = gradebookService;
		this.sectionManager = sectionManager;
	}

	/**
	 * Create/Update Gradebook item
	 * 
	 * @param lessonDao
	 * @param course
	 * @param previousFireTime
	 */
	public void updateCourseLessonPlan(NWUCourseLessonDao lessonDao, NWUCourse course, Date previousFireTime) {

		Assignment assignment = null;
		String assignmentName = null;
		for (NWUGBLesson lesson : course.getLessons()) {			

//			if (previousFireTime != null && !lesson.getAuditDateTime().isAfter(previousFireTime.toInstant())) {
//				continue;
//			}

			if (lesson.getEfundiGradebookId() != null) {
				try {
					assignment = gradebookService.getAssignmentByIDEvenIfRemoved(lesson.getEfundiGradebookId());
					
					if (assignment.isRemoved()) {
						gradebookService.restoreAssignment(assignment.getId());
					}
					
					assignmentName = getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode());

					if (!assignmentName.equals(assignment.getName())) {
						assignment.setName(assignmentName);
						gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
					}
				} catch (AssessmentNotFoundException anf) {

					// If a user deleted a Gradebook item but the lesson plan table has already been
					// updated with the efundiGradebookId, a new one must be created
					createNewAssignment(lessonDao, course, lesson);
				}
			} else {
				createNewAssignment(lessonDao, course, lesson);
			}
		}
	}

	/**
	 * Creates a new Assignment
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lesson
	 */
	private void createNewAssignment(NWUCourseLessonDao lessonDao, NWUCourse course, NWUGBLesson lesson) {
		Assignment assignment;
		try {
			assignment = new Assignment();
			assignment.setName(getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode()));// classTestName-(classTestCode)
			assignment.setPoints(lesson.getClassTestMaxScore());
			assignment.setReleased(false);
			Long assignmentId = gradebookService.addAssignment(course.getEfundiSiteId(), assignment);
			lesson.setEfundiGradebookId(assignmentId);
			lessonDao.updateLesson(lesson);

			log.info("Gradebook item created for Lesson plan: " + lesson);

		} catch (ConflictingAssignmentNameException cane) {
			// Don't know why this can happen. Transaction related, perhaps. Not
			// that important, anyway.
			log.warn("Failed to set gb item name to: ",
					getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode()));
		}
	}

	/**
	 * Returns Assignment Name
	 * 
	 * @param classTestName
	 * @param classTestCode
	 * @return
	 */
	private String getAssignmentName(String classTestName, String classTestCode) {
		return "SP-" + classTestName + "-(" + classTestCode + ")";
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
		List<EnrollmentRecord> siteEnrollments = sectionManager.getSiteEnrollments(course.getEfundiSiteId());

		log.info("Enrollment list for SiteId = " + course.getEfundiSiteId() + "; StudentUserIds = "
				+ getStudentUserIds(siteEnrollments));

		Long lessonId = null;
		for (Assignment assignment : assignments) {

			lessonId = getLessonId(course, assignment.getId());
			if (lessonId == null) {
				log.error("Could not find lessonId for GradebookId " + assignment.getId() + " : " + course);
				continue;
			}
			
			List<GradeDefinition> gradesForStudentsList = gradebookService
					.getGradesForStudentsForItem(gradebook.getUid(), assignment.getId(), getStudentUuidsList(siteEnrollments));
			
			int gradesCount = gradesForStudentsList == null ? 0 : gradesForStudentsList.size();
			log.info("Student grades list: gradebook.getUid() = " + gradebook.getUid() + "; assignment.getId() = "
					+ assignment.getId() + "; found " + gradesCount + " grades");

			List<NWULessonGrade> lessonGrades = lessonGradeDao.getAllGradesByLessonId(lessonId);
			if (lessonGrades.isEmpty() && !gradesForStudentsList.isEmpty()) {
				log.info("No Grades found for LessonId: " + lessonId);

				NWULessonGrade lessonGrade = null;
				String nwuNumber = null;
				for (GradeDefinition gradeDefinition : gradesForStudentsList) {

					nwuNumber = getValidStudentEid(gradeDefinition.getStudentUid());
					if (nwuNumber == null) {
						log.error("Could not find user " + gradeDefinition.getStudentUid() + ": " + course);
						continue;
					}

					lessonGrade = new NWULessonGrade();
					lessonGrade.setLessonId(lessonId);
					lessonGrade.setNwuNumber(Integer.parseInt(nwuNumber));
					lessonGrade.setGrade(Double.parseDouble(gradeDefinition.getGrade()));
					lessonGrade.setAuditDateTime(Instant.now());
					lessonGradeDao.updateLessonGrade(lessonGrade);

					log.info("Grade recorded: " + lessonGrade);
				}
			} else {
				String nwuNumber = null;

				for (GradeDefinition gradeDefinition : gradesForStudentsList) {

					nwuNumber = getValidStudentEid(gradeDefinition.getStudentUid());
					if (nwuNumber == null) {
						log.error("Could not find user " + gradeDefinition.getStudentUid() + ": " + course);
						continue;
					}

					NWULessonGrade lessonGrade = getLessonGradeForStudent(lessonGrades, nwuNumber);
					if (lessonGrade == null) {

						lessonGrade = new NWULessonGrade();
						lessonGrade.setLessonId(lessonId);
						lessonGrade.setNwuNumber(Integer.parseInt(nwuNumber));
						lessonGrade.setGrade(Double.parseDouble(gradeDefinition.getGrade()));
						lessonGrade.setAuditDateTime(Instant.now());
						lessonGradeDao.updateLessonGrade(lessonGrade);

						log.info("Grade recorded: " + lessonGrade);

					} else if (!lessonGrade.getGrade().equals(Double.parseDouble(gradeDefinition.getGrade()))) {

						lessonGrade.setGrade(Double.parseDouble(gradeDefinition.getGrade()));
						lessonGrade.setAuditDateTime(Instant.now());
						lessonGradeDao.updateLessonGrade(lessonGrade);

						log.info("Grade updated: " + lessonGrade);
					}
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
	private Long getLessonId(NWUCourse course, Long id) {
		List<NWUGBLesson> lessons = course.getLessons();
		for (NWUGBLesson lesson : lessons) {
			if (lesson.getEfundiGradebookId() != null && lesson.getEfundiGradebookId().equals(id)) {
				return lesson.getId();
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
}
