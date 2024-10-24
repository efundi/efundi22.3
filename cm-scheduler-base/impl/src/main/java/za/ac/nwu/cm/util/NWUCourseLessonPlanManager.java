package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.CourseGrade;
import org.sakaiproject.service.gradebook.shared.GradeDefinition;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.tool.gradebook.Gradebook;
import org.sakaiproject.user.api.UserDirectoryService;

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
		for (NWUGBLesson lesson : lessons) {

			if (lesson.getEfundiGradebookId() != null) {

				assignment = gradebookService.getAssignmentByID(lesson.getEfundiGradebookId());

				if (assignment != null && !lesson.getClassTestName().equals(assignment.getName())) {
					assignment.setName(lesson.getClassTestName());
					gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
				}

			} else {
				try {
					assignment = new Assignment();
					assignment.setName(lesson.getClassTestName());
					assignment.setPoints(lesson.getClassTestMaxScore());
					assignment.setReleased(false);
					Long assignmentId = gradebookService.addAssignment(course.getEfundiSiteId(), assignment);
					lesson.setEfundiGradebookId(assignmentId);
					lessonDao.updateLesson(lesson);

					log.info("Gradebook item created for Lesson plan: " + lesson);

				} catch (ConflictingAssignmentNameException cane) {
					// Don't know why this can happen. Transaction related, perhaps. Not
					// that important, anyway.
					log.warn("Failed to set gb item name to: {}", lesson.getClassTestName());
				}
			}
		}
	}

	/**
	 * Add/update Student grade
	 * 
	 * @param lessonGradeDao
	 * @param course
	 */
	public void updateLessonPlanStudentGrades(NWULessonGradeDao lessonGradeDao, NWUCourse course) {

		Gradebook gradebook = (Gradebook) gradebookService.getGradebook(course.getEfundiSiteId());
		List<Assignment> assignments = gradebookService.getAssignments(gradebook.getUid());
		NWULessonGrade lessonGrade = null;

		for (Assignment assignment : assignments) {
			List<GradeDefinition> gradesForStudentsList = gradebookService.getGradesForStudentsForItem(gradebook.getUid(), assignment.getId(),
					getStudentUuidsList(course));
			log.info("gradesForStudentsList: " + gradesForStudentsList);

			List<NWULessonGrade> lessonGrades = lessonGradeDao.getAllGradesByLessonId(assignment.getId());
			if (lessonGrades.isEmpty()) {
				for (GradeDefinition gradeDefinition : gradesForStudentsList) {

					lessonGrade = new NWULessonGrade();
					lessonGrade.setLessonId(assignment.getId());
//					lessonGrade.setNwuNumber(gradeDefinition.getStudentUid());
					lessonGrade.setGrade(Double.parseDouble(gradeDefinition.getGrade()));
					lessonGrade.setAuditDateTime(Instant.now());
//					lessonGradeDao.updateLessonGrade(lessonGrade);					
					
				}
			}
		}

//		List<NWUGBLesson> lessons = course.getLessons();
//		for (NWUGBLesson lesson : lessons) {
//
//			List<NWULessonGrade> studentGrades = lessonGradeDao.getAllGradesByLessonId(lesson.getId());
//
//			Assignment assignment = gradebookService.getAssignmentByID(lesson.getEfundiGradebookId());
//
//			if (studentGrades.isEmpty()) {
//				// add grades
////				Set gbSet = gradebookService.getGradebookGradeMappings(lesson.getEfundiGradebookId());
////				log.info("gbSet: " + gbSet);
////				Set gbSet1 = gradebookService.getGradebookGradeMappings("" + lesson.getEfundiGradebookId());
////				log.info("gbSet1: " + gbSet1);
//				List<GradeDefinition> gbSet2 = gradebookService.getGradesForStudentsForItem(null, null,
//						getStudentUuidsList(course));
//				log.info("gbSet2: " + gbSet2);
//				Map<String, CourseGrade> gbSet3 = gradebookService
//						.getCourseGradeForStudents("" + lesson.getEfundiGradebookId(), getStudentUuidsList(course));
//				log.info("gbSet3: " + gbSet3);
//			} else {
//				// check for grade changes and update
//
//				for (NWULessonGrade lessonGrade : studentGrades) {
//
//					lessonGradeDao.updateLessonGrade(null);
//				}
//			}
//		}
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
			studentNumberList.add("" + student.getNwuNumber());
		}
		return studentNumberList;
	}
}
