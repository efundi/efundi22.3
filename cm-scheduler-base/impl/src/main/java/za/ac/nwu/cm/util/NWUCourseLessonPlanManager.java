package za.ac.nwu.cm.util;

import java.util.List;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.UserDirectoryService;

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

	public NWUCourseLessonPlanManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService, final GradebookService gradebookService) {

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
						
			if(lesson.getEfundiGradebookId() != null) {
//				if lesson.getAuditDateTime() > previousJobrun date or id date is null else do all
				
//				Assignment assignment = gradebookService.getAssignmentByID(lesson.getEfundiGradebookId());
//				assignment.getName()				
				
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
	 * @param lessons
	 */
	public void updateLessonPlanStudentGrades(NWULessonGradeDao lessonGradeDao, NWUCourse course,
			List<NWUGBLesson> lessons) {
		for (NWUGBLesson lesson : lessons) {
			
			List<NWULessonGrade> studentGrades = lessonGradeDao.getAllGradesByLessonId(lesson.getId());
						
			if(studentGrades.isEmpty()) {
				//add grades
			} else {
				//check for grade changes and update
			}
		}
	}
}
