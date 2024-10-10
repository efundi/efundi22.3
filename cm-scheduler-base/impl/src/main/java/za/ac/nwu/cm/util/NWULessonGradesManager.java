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
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBLesson;
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
	
	private GradebookService gradebookService;
	public NWULessonGradesManager(final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService, final GradebookService gradebookService) {
		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;
		this.gradebookService = gradebookService;
	}
	/**
	 * Create/Update Gradebook item
	 * 
	 * @param 	  
	 * @param 
	 * 	 * @param 	 */
	public void updateLessonGrades(NWUGBLesson lesson) {
		
		}


}