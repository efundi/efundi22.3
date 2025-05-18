package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.section.api.SectionManager;
import org.sakaiproject.service.gradebook.shared.AssessmentNotFoundException;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.UserDirectoryService;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.dao.NWUExamLessonDao;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBExamLesson;

/**
 * This class manages Exam Lessons in Gradebook for courses.
 * 
 * @author JC Gillman
 * 
 */
@Slf4j
public class NWUCourseExamLessonManager {

	private UserDirectoryService userDirectoryService;

	private ServerConfigurationService serverConfigurationService;

	private SiteService siteService;

	private GradebookService gradebookService;

	private SectionManager sectionManager;

	public NWUCourseExamLessonManager(final UserDirectoryService userDirectoryService,
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
	 * @param examLessonDao
	 * @param course
	 * @param previousFireTime
	 */
	public void updateExamLessonPlan(NWUExamLessonDao examLessonDao, NWUCourse course, Date previousFireTime) {

		Assignment assignment = null;
		String assignmentName = null;

		// Get all Exam Lessons for Courses
		List<NWUGBExamLesson> examLessons = examLessonDao.getExamLessonsByCourseAndDate(course.getCampus(), course.getTerm(), course.getCourseCode(), course.getSectionCode());

		if (examLessons == null || examLessons.isEmpty()) {
			log.info("No Exam Lessons found: " + course);
		}
		for (NWUGBExamLesson examLesson : examLessons) {

//			if (previousFireTime != null && !examLessons.getAuditDateTime().isAfter(previousFireTime.toInstant())) {
//				continue;
//			}

			if (examLesson.getEfundiGradebookId() != null) {
				try {
					assignment = gradebookService.getAssignmentByIDEvenIfRemoved(examLesson.getEfundiGradebookId());

					if (assignment.isRemoved()) {
						gradebookService.restoreAssignment(assignment.getId());
					}

					assignmentName = getAssignmentName(examLesson);

					if (!assignmentName.equals(assignment.getName())) {
						assignment.setName(assignmentName);
						gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
					}
					assignmentName = null;
					
					if (!Double.valueOf(examLesson.getMaxScore().doubleValue()).equals(assignment.getPoints())) {
						assignment.setPoints(examLesson.getMaxScore().doubleValue());
						gradebookService.updateAssignment(course.getEfundiSiteId(), assignment.getId(), assignment);
					}
				} catch (AssessmentNotFoundException anf) {

					// If a user deleted a Gradebook item but the exam lesson table has already been
					// updated with the efundiGradebookId, a new one must be created
					createNewAssignment(examLessonDao, course.getEfundiSiteId(), examLesson);
				}
			} else {
				createNewAssignment(examLessonDao, course.getEfundiSiteId(), examLesson);
			}
		}
	}

	/**
	 * Creates a new Assignment
	 * 
	 * @param examLessonDao
	 * @param efundiSiteId
	 * @param lesson
	 */
	private void createNewAssignment(NWUExamLessonDao examLessonDao, String efundiSiteId, NWUGBExamLesson examLesson) {
		Assignment assignment;
		try {
			assignment = new Assignment();
			assignment.setName(getAssignmentName(examLesson));// SP-classTestName-(classTestCode)-classTestMaxScore
			assignment.setPoints(examLesson.getMaxScore().doubleValue());
			assignment.setReleased(false);
			Long assignmentId = gradebookService.addAssignment(efundiSiteId, assignment);
			examLesson.setEfundiGradebookId(assignmentId);
			examLesson.setAuditDateTime(Instant.now());			
			examLessonDao.updateExamLesson(examLesson);

			log.info("Gradebook item created for Exam Lesson: " + examLesson);

		} catch (ConflictingAssignmentNameException cane) {
			// Don't know why this can happen. Transaction related, perhaps. Not
			// that important, anyway.
			log.warn("Failed to set gb item name to: ",	getAssignmentName(examLesson));
		}
	}

	/**
	 * Returns Assignment Name
	 * 
	 * @param lesson
	 * @return
	 */
	private String getAssignmentName(NWUGBExamLesson examLesson) {
		return "SE-" + examLesson.getExamLessonName() + "-(" + examLesson.getExamLessonCode() + ")-" + examLesson.getMaxScore().intValue();
	}
}
