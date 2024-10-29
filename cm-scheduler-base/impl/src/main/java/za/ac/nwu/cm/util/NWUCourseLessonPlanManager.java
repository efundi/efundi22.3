package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.GradeDefinition;
import org.sakaiproject.service.gradebook.shared.GradebookService;
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
import za.ac.nwu.api.model.NWUStudentEnrollment;

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

	public NWUCourseLessonPlanManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService,
			final GradebookService gradebookService) {

		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;
		this.gradebookService = gradebookService;
	}

	/**
	 * Create/Update Gradebook item
	 * 
	 * @param lessonDao
	 * @param course
	 * @param lessons
	 */
	public void updateCourseLessonPlan(NWUCourseLessonDao lessonDao, NWUCourse course, List<NWUGBLesson> lessons) {

		Assignment assignment = null;
		String assignmentName = null;
		for (NWUGBLesson lesson : lessons) {

			if (lesson.getEfundiGradebookId() != null) {

				assignment = gradebookService.getAssignmentByID(lesson.getEfundiGradebookId());
				assignmentName = getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode());
				
				if (assignment != null && !assignmentName.equals(assignment.getName())) {
					assignment.setName(assignmentName);
					gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
				}

			} else {
				try {
					assignment = new Assignment();
					assignment.setName(getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode()));//classTestName-(classTestCode)
					assignment.setPoints(lesson.getClassTestMaxScore());
					assignment.setReleased(false);
					Long assignmentId = gradebookService.addAssignment(course.getEfundiSiteId(), assignment);
					lesson.setEfundiGradebookId(assignmentId);
					lessonDao.updateLesson(lesson);

					log.info("Gradebook item created for Lesson plan: " + lesson);

				} catch (ConflictingAssignmentNameException cane) {
					// Don't know why this can happen. Transaction related, perhaps. Not
					// that important, anyway.
					log.warn("Failed to set gb item name to: ", getAssignmentName(lesson.getClassTestName(), lesson.getClassTestCode()));
				}
			}
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

		Long lessonId = null;
		for (Assignment assignment : assignments) {

			List<GradeDefinition> gradesForStudentsList = gradebookService
					.getGradesForStudentsForItem(gradebook.getUid(), assignment.getId(), getStudentUuidsList(course));
			log.info("Student grades list: gradebook.getUid() = " + gradebook.getUid() + "; assignment.getId() = "
					+ assignment.getId() + gradesForStudentsList);

			lessonId = getLessonId(course, assignment.getId());
			if (lessonId == null) {
				log.error("Could not find lessonId for GradebookId " + assignment.getId() + ": " + course);
				continue;
			}
			List<NWULessonGrade> lessonGrades = lessonGradeDao.getAllGradesByLessonId(lessonId);
			if (lessonGrades.isEmpty() && !gradesForStudentsList.isEmpty()) {
				log.info("No Grades found for LessonId: " + lessonId);

				NWULessonGrade lessonGrade = null;
				String nwuNumber = null;
				for (GradeDefinition gradeDefinition : gradesForStudentsList) {

					nwuNumber = getStudentEid(gradeDefinition.getStudentUid());
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

					nwuNumber = getStudentEid(gradeDefinition.getStudentUid());
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
			if (nwuNumber.equals("" + lessonGrade.getNwuNumber())) {
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
	 * Returns the user Eid
	 * 
	 * @param userId
	 * @return
	 */
	private String getStudentEid(String userId) {
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
	 * @param course
	 * @return
	 */
	private List<String> getStudentUuidsList(NWUCourse course) {

		List<String> studentNumberList = new ArrayList<String>();
		for (NWUStudentEnrollment student : course.getStudents()) {
			User user = null;
			try {
				user = userDirectoryService.getUserByEid("" + student.getNwuNumber());
				studentNumberList.add(user.getId());

				log.info("Found User with eid = " + student.getNwuNumber() + "; Id = " + user.getId());
			} catch (UserNotDefinedException e) {
				log.error("User with eid " + student.getNwuNumber() + " not found: " + e.toString());
			}
		}
		return studentNumberList;
	}
}
