package za.ac.nwu.cm.util;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.service.gradebook.shared.Assignment;
import org.sakaiproject.service.gradebook.shared.ConflictingAssignmentNameException;
import org.sakaiproject.service.gradebook.shared.CourseGrade;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.assignment.api.AssignmentService;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.dao.NWUCourseLessonDao;
import za.ac.nwu.api.dao.NWULessonGradeDao;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBLesson;
import za.ac.nwu.api.model.NWULessonGrade;
import za.ac.nwu.api.model.NWUStudentEnrollment;

/**
 * This class manages Lessons grades.
 * 
 * @author BMcL
 * 
 */
@Slf4j
public class NWULessonGradesManager {
	private UserDirectoryService userDirectoryService;
	private ServerConfigurationService serverConfigurationService;
	private SiteService siteService;
	private AssignmentService assignmentService;

	private GradebookService gradebookService;

	public NWULessonGradesManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService,
			final GradebookService gradebookService, final AssignmentService assignmentService) {
		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;
		this.gradebookService = gradebookService;
	}

	public NWULessonGradesManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Create or update a lesson grade entry
	 * @param lessonGradeDao 
	 * 
	 * @param lessons
	 * @param lessons
	 */
	public void updateLessonGrades(NWULessonGradeDao lessonGradeDao, List<NWUGBLesson> lessons, List<NWUStudentEnrollment> students, Date previousFireTime ) {

			
		
		}
	}