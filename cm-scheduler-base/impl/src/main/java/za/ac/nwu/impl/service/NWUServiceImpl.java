package za.ac.nwu.impl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionException;
import org.sakaiproject.authz.api.AuthzGroup;
import org.sakaiproject.authz.api.AuthzGroupService;
import org.sakaiproject.authz.api.SecurityAdvisor;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.coursemanagement.api.CourseManagementAdministration;
import org.sakaiproject.coursemanagement.api.CourseManagementService;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;
import org.sakaiproject.exception.IdInvalidException;
import org.sakaiproject.exception.IdUsedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.id.api.IdManager;
import org.sakaiproject.service.gradebook.shared.GradebookService;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.SitePage;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.site.api.ToolConfiguration;
import org.sakaiproject.tool.api.Session;
import org.sakaiproject.tool.api.SessionManager;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserAlreadyDefinedException;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserIdInvalidException;
import org.sakaiproject.user.api.UserNotDefinedException;
import org.sakaiproject.user.api.UserPermissionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.dao.NWUCourseDao;
import za.ac.nwu.api.dao.NWUCourseEnrollmentDao;
import za.ac.nwu.api.dao.NWUCourseLecturerDao;
import za.ac.nwu.api.dao.NWUCourseLessonDao;
import za.ac.nwu.api.dao.NWULessonGradeDao;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBLesson;
import za.ac.nwu.api.model.NWULecturer;
import za.ac.nwu.api.model.NWULessonGrade;
import za.ac.nwu.api.model.NWUStudentEnrollment;
import za.ac.nwu.api.service.NWUService;
import za.ac.nwu.cm.util.NWUCourseLessonPlanManager;
import za.ac.nwu.cm.util.NWUCourseManager;
import za.ac.nwu.cm.util.NWULessonGradesManager;
import za.ac.nwu.cm.util.Utility;

/**
 * NWUServiceImpl - NWUService implementation
 * 
 * @author JC Gillman
 *
 */
@Slf4j
public class NWUServiceImpl implements NWUService, ApplicationContextAware {

	private UserDirectoryService userDirectoryService;
	private ServerConfigurationService serverConfigurationService;
	private AuthzGroupService authzGroupService;
	private SessionManager sessionManager;
	private SiteService siteService;
	private SecurityService securityService;
	private CourseManagementService cmService;
	private CourseManagementAdministration cmAdmin;
	private IdManager idManager;
	private GradebookService gradebookService;

	private ApplicationContext applicationContext;

	private NWUCourseDao courseDao;
	private NWUCourseEnrollmentDao enrollmentDao;
	private NWUCourseLecturerDao lecturerDao;
	private NWUCourseLessonDao lessonDao;
	private NWULessonGradeDao lessonGradeDao;

	private static final String BLANK_SPACE = " ";
	private static final String HYPHEN = "-";

	private static final String SEMESTER_S = "S";
	private static final String SEMESTER_SEM = "SEM";
	private static final String STUDENT_ROLE = "Student";
	private static final String INSTRUCTOR_ROLE = "Instructor";

	private static final String TOOL_OVERVIEW_TITEL = "Overview";

	private static final String TOOL_ID_SYNOPTIC_ANNOUNCEMENT = "sakai.synoptic.announcement";
	private static final String TOOL_ID_SUMMARY_CALENDAR = "sakai.summary.calendar";
	private static final String TOOL_ID_SYNOPTIC_CHAT = "sakai.synoptic.chat";
	private static final String TOOL_ID_SYNOPTIC_MESSAGECENTER = "sakai.synoptic.messagecenter";

	private final static List<String> DEFAULT_TOOL_ID_MAP;
	static {
		DEFAULT_TOOL_ID_MAP = new ArrayList<String>();
//		DEFAULT_TOOL_ID_MAP.add("sakai.announcements");
		DEFAULT_TOOL_ID_MAP.add("sakai.siteinfo");
//		DEFAULT_TOOL_ID_MAP.add("sakai.resources");
//		DEFAULT_TOOL_ID_MAP.add("sakai.syllabus");
//		DEFAULT_TOOL_ID_MAP.add("sakai.lessonbuildertool");
//		DEFAULT_TOOL_ID_MAP.add("sakai.schedule");
//		DEFAULT_TOOL_ID_MAP.add("sakai.forums");
//		DEFAULT_TOOL_ID_MAP.add("sakai.assignment.grades");
//		DEFAULT_TOOL_ID_MAP.add("sakai.samigo");
//		DEFAULT_TOOL_ID_MAP.add("sakai.rubrics");
		DEFAULT_TOOL_ID_MAP.add("sakai.gradebookng");
//		DEFAULT_TOOL_ID_MAP.add("sakai.messages");
		DEFAULT_TOOL_ID_MAP.add("sakai.sitestats");
//		DEFAULT_TOOL_ID_MAP.add("sakai.poll");
//		DEFAULT_TOOL_ID_MAP.add("sakai.dropbox");
//		DEFAULT_TOOL_ID_MAP.add("sakai.chat");
//		DEFAULT_TOOL_ID_MAP.add("sakai.commons");
//		DEFAULT_TOOL_ID_MAP.add("sakai.mailtool");
//		DEFAULT_TOOL_ID_MAP.add("sakai.mailbox");
	}

	private final static Map<String, String> TOOLS_WITH_SYNOPTIC_ID_MAP;
	static {
		TOOLS_WITH_SYNOPTIC_ID_MAP = new HashMap<String, String>();
		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.announcements", TOOL_ID_SYNOPTIC_ANNOUNCEMENT);
		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.schedule", TOOL_ID_SUMMARY_CALENDAR);
		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.chat", TOOL_ID_SYNOPTIC_CHAT);

		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.messages", TOOL_ID_SYNOPTIC_MESSAGECENTER);
		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.forums", TOOL_ID_SYNOPTIC_MESSAGECENTER);
		TOOLS_WITH_SYNOPTIC_ID_MAP.put("sakai.messagecenter", TOOL_ID_SYNOPTIC_MESSAGECENTER);
	}

	public NWUServiceImpl() {
	}

	public void init() {
	}

	/**
	 * Reads all course data from table cm_curriculum_course and create eFundi CM
	 * data
	 * 
	 * @throws JobExecutionException
	 */
	public void updateNWUCourseManagement() throws JobExecutionException {

		// Get all existing course from Database for current

//		Calendar cal = Calendar.getInstance();
//		int year = serverConfigurationService.getInt("nwu.cm.year", 0);
//		year = year != 0 ? year : cal.get(Calendar.YEAR);
//		List<NWUCourse> courses = courseDao.getAllCoursesByYear(year);// Also by Status, add Status column, if empty
		// process, otherwise skip. after insert update
		// to inserted or whatever. modifiedDate?

		// Get all existing course from Database for current year and no Sakai site id
//		List<NWUCourse> courses = courseDao.FindAllCoursesWithNoSiteId(Calendar.getInstance().get(Calendar.YEAR));


		// Get all CM data with no Sakai site id
		List<NWUCourse> courses = getCourseDao().getAllCoursesWithNoSiteId();
		if (courses.isEmpty()) {
			log.info("No courses found ");
		}
		if (!courses.isEmpty()) {

			Utility.printCoursesInfo(courses);

			// Become admin in order to add sites
//			SecurityAdvisor yesMan = new SecurityAdvisor() {
//				public SecurityAdvice isAllowed(String userId, String function, String reference) {
//					return SecurityAdvice.ALLOWED;
//				}
//			};

			try {

				loginToSakai();

//				securityService.pushAdvisor(yesMan);

				NWUCourseManager courseManager = new NWUCourseManager(cmAdmin, cmService, userDirectoryService,
						serverConfigurationService, siteService);

				for (NWUCourse course : courses) {

					NWULecturer lecturer = course.getLecturer();
					if (lecturer == null) {
						log.error("Course must have an Instuctor: " + course);
						continue;
					}

					// Create Course Management data
					courseManager.createCourseManagement(course);

					// Create Course Sites
					createEFundiCoureSite(course);
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);

				throw new JobExecutionException("updateNWUCourseManagement failed: " + e.getMessage());
			} finally {
//				securityService.popAdvisor(yesMan);
				logoutFromSakai();
			}
		}
	}

	/**
	 * Updating NWU Course Sites from CM data
	 */
	public void updateNWUeFundiCourseSites() {

		// Get all existing course from Database for current year and no Sakai site id
//		List<NWUCourse> courses = courseDao.getAllCoursesByYearAndSiteId(Calendar.getInstance().get(Calendar.YEAR));
		
		// Get all CM data with no Sakai site id
		List<NWUCourse> courses = getCourseDao().getAllCoursesWithNoSiteId();
		if (!courses.isEmpty()) {

			// Become admin in order to add sites
			SecurityAdvisor yesMan = new SecurityAdvisor() {
				public SecurityAdvice isAllowed(String userId, String function, String reference) {
					return SecurityAdvice.ALLOWED;
				}
			};

			try {

				loginToSakai();
				securityService.pushAdvisor(yesMan);

				for (NWUCourse course : courses) {

					NWULecturer lecturer = course.getLecturer();
					if (lecturer == null) {
						log.error("Course must have an Instuctor: " + course);
						continue;
					}

					createEFundiCoureSite(course);
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			} finally {
				securityService.popAdvisor(yesMan);
				logoutFromSakai();
			}
		}
	}

	/**
	 * Reads all student enrollment data for courses and add/remove to rosters
	 * accordingly
	 * 
	 * @throws JobExecutionException
	 * 
	 */
	public void updateNWUCourseEnrollments(Date previousFireTime) throws JobExecutionException {

//		Calendar cal = Calendar.getInstance();
//		int year = serverConfigurationService.getInt("nwu.cm.year", 0);
//		year = year != 0 ? year : cal.get(Calendar.YEAR);
//		List<NWUCourse> courses = courseDao.getAllCoursesByYear(year);
		
		// Get all CM data with Sakai site id
		List<NWUCourse> courses = getCourseDao().getAllCoursesWithSiteId();

		if (courses.isEmpty()) {
			log.info("No courses found ");
		}
		if (!courses.isEmpty()) {
			
			Utility.printCoursesInfo(courses);
			log.info("Job PreviousFireTime: " + previousFireTime);

			try {

				loginToSakai();
//				securityService.pushAdvisor(yesMan);

				NWUCourseManager courseManager = new NWUCourseManager(cmAdmin, cmService, userDirectoryService,
						serverConfigurationService, siteService);

				for (NWUCourse course : courses) {

//					NWULecturer lecturer = course.getLecturer();
//					if (lecturer == null) {
//						log.error("Course must have an Instuctor: " + course);
//						continue;
//					}

					// Manage Course Enrollment
					List<NWUStudentEnrollment> enrollmentList = getEnrollmentDao()
							.getEnrollmentsByCourseIdAndDate(course.getId(), previousFireTime);
					
					if (enrollmentList != null && !enrollmentList.isEmpty()) {
						courseManager.updateCourseEnrollment(course, enrollmentList);
					} else {
						log.info("No students found ");
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);

				throw new JobExecutionException("updateNWUCourseEnrollments failed: " + e.getMessage());
			} finally {
//				securityService.popAdvisor(yesMan);
				logoutFromSakai();
			}
		}
	}

	/**
	 * Reads all Lecturer data for courses and add/remove accordingly
	 * 
	 * @throws JobExecutionException
	 * 
	 */
	public void updateNWUCourseLecturers(Date previousFireTime) throws JobExecutionException {

//		Calendar cal = Calendar.getInstance();
//		int year = serverConfigurationService.getInt("nwu.cm.year", 0);
//		year = year != 0 ? year : cal.get(Calendar.YEAR);
//		List<NWUCourse> courses = courseDao.getAllCoursesByYear(year);

		// Get all CM data with Sakai site id
		List<NWUCourse> courses = getCourseDao().getAllCoursesWithSiteId();
		
		if (courses.isEmpty()) {
			log.info("No courses found ");
		}
		if (!courses.isEmpty()) {
			
			Utility.printCoursesInfo(courses);
			log.info("Job PreviousFireTime: " + previousFireTime);

			try {

				loginToSakai();
//				securityService.pushAdvisor(yesMan);

				NWUCourseManager courseManager = new NWUCourseManager(cmAdmin, cmService, userDirectoryService,
						serverConfigurationService, siteService);

				for (NWUCourse course : courses) {

					NWULecturer lecturer = course.getLecturer();
					if (lecturer == null) {
						log.error("Course must have an Instuctor: " + course);
						continue;
					}

					// Manage Course Lecturers
					List<NWULecturer> lecturers = getLecturerDao().getLecturersByCourseIdAndDate(course.getId(),
							previousFireTime);
					if (lecturers != null && !lecturers.isEmpty()) {
						courseManager.updateCourseLecturers(course);
					} else {
						log.info("No lecturers found ");
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);

				throw new JobExecutionException("updateNWUCourseLecturers failed: " + e.getMessage());
			} finally {
//				securityService.popAdvisor(yesMan);
				logoutFromSakai();
			}
		}
	}

	/**
	 * Reads all Lesson plan data for courses and create Gradebook items accordingly
	 * 
	 * @throws JobExecutionException
	 * 
	 */
	public void updateNWUCourseLessonPlans(Date previousFireTime) throws JobExecutionException {

		// Get all CM data with Sakai site id
		List<NWUCourse> courses = getCourseDao().getAllCoursesWithSiteId();
		
		if (courses.isEmpty()) {
			log.info("No courses found ");
		}
		if (!courses.isEmpty()) {
			
			Utility.printCoursesInfo(courses);
			log.info("Job PreviousFireTime: " + previousFireTime);

			try {

				loginToSakai();
//				securityService.pushAdvisor(yesMan);

				NWUCourseLessonPlanManager lessonManager = new NWUCourseLessonPlanManager(userDirectoryService, serverConfigurationService, siteService, gradebookService);

				for (NWUCourse course : courses) {

//					NWULecturer lecturer = course.getLecturer();
//					if (lecturer == null) {
//						log.error("Course must have an Instuctor: " + course);
//						continue;
//					}

					// Manage Course Lesson plans
//					List<NWUGBLesson> lessons = getLessonDao().getLessonsByCourseIdAndDate(course.getId(),
//							previousFireTime);
					List<NWUGBLesson> lessons = course.getLessons();
					
					if (lessons != null && !lessons.isEmpty()) {
						lessonManager.updateCourseLessonPlan(getLessonDao(), course, lessons);
					} else {
						log.info("No Lesson plans found ");
					}
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);

				throw new JobExecutionException("updateNWUCourseLessonPlans failed: " + e.getMessage());
			} finally {
//				securityService.popAdvisor(yesMan);
				logoutFromSakai();
			}
		}
	}

	/**
	 * Create eFundi course site
	 * 
	 * @param course
	 * @return
	 */
	private Site createEFundiCoureSite(NWUCourse course) {

		log.info("NWU - Creating unpublished eFundi Course Site");
		Site newSite = null;
		try {
			String siteId = idManager.createUuid();
			String description = course.getCourseDescr();
			newSite = siteService.addSite(siteId, "course");
			newSite.setTitle(generateSiteName(course));
			newSite.setDescription(description);
			newSite.setShortDescription(description);
			newSite.setPublished(false);
			newSite.setJoinable(false);

			addDefaultSakaiTools(newSite);

			String term = course.getTerm();
			String termEid = course.getTerm();

			ResourcePropertiesEdit propEdit = newSite.getPropertiesEdit();
			propEdit.addProperty(Site.PROP_SITE_TERM, term);
			propEdit.addProperty(Site.PROP_SITE_TERM_EID, termEid);

			// add lecturer user as the maintainer
			newSite.addMember("" + course.getLecturer().getInstructorNumber(), newSite.getMaintainRole(), true, false);

			User user = userDirectoryService.getUserByEid("" + course.getLecturer().getInstructorNumber());

			propEdit.addProperty(Site.PROP_SITE_CONTACT_NAME, user.getDisplayName());
			propEdit.addProperty(Site.PROP_SITE_CONTACT_EMAIL, user.getEmail());

			siteService.save(newSite);

			String realm = siteService.siteReference(siteId);

			AuthzGroup realmEdit = authzGroupService.getAuthzGroup(realm);
			String courseOfferingReference = Utility.getCourseOfferingReference(course);
			realmEdit.setProviderGroupId(courseOfferingReference);
			authzGroupService.save(realmEdit);
			siteService.save(newSite);

			course.setEfundiSiteId(newSite.getId());
			courseDao.updateCourse(course);

			log.info("NWU - New Course site created: " + siteId);

		} catch (IdInvalidException | PermissionException e) {
			log.error("createSakaiSite: " + e.getMessage(), e);
		} catch (IdUsedException e) {
			log.error("createSakaiSite - IdUsedException: " + e.getId(), e);
		} catch (Exception e) {
			log.error("createSakaiSite: " + e.getMessage(), e);
		}
		return newSite;
	}

	/**
	 * Adds list of default Sakai Tools
	 * 
	 * @param site
	 */
	private void addDefaultSakaiTools(Site site) {

		SitePage overviewPage = site.addPage();
		ToolConfiguration overviewToolConfig = overviewPage.addTool("sakai.iframe.site");

		overviewPage.getPropertiesEdit().addProperty(SitePage.IS_HOME_PAGE, Boolean.TRUE.toString());
		overviewToolConfig.setTitle(TOOL_OVERVIEW_TITEL);

		int synopticToolIndex = 0;

		for (String toolId : DEFAULT_TOOL_ID_MAP) {

			if (TOOLS_WITH_SYNOPTIC_ID_MAP.containsKey(toolId)) {

				// add tool
				site.addPage().addTool(toolId);

				ToolConfiguration toolConfig = overviewPage.addTool(toolId);
				if (null != toolConfig) {
					toolConfig.setLayoutHints(synopticToolIndex + ",1");
					synopticToolIndex++;
				}
			} else {
				site.addPage().addTool(toolId);
			}
		}

		// for synoptic tools
		if (synopticToolIndex > 0) {
			overviewPage.setLayout(SitePage.LAYOUT_DOUBLE_COL);
		}
	}
	
		public void updateNWULessonGradesUpdateJob(Date previousFireTime) throws JobExecutionException {

	
			
			List<NWUCourse> courses = getCourseDao().getAllCoursesWithSiteId();
			NWUCourseEnrollmentDao nwuCourseEnrollDAO;
			NWULessonGradeDao nwuLessonGradeDao;			
			
			if (courses.isEmpty()) {
				log.info("No courses found ");
			}
			if (!courses.isEmpty()) {				
			//	Utility.printCoursesInfo(courses);
				log.info("Job PreviousFireTime: " + previousFireTime);

				for (NWUCourse course : courses) {
					
					List<NWUGBLesson> lessons = course.getLessons();
					
					if (lessons.isEmpty()) {
						log.info("No lessons found ");
					}
					if (!lessons.isEmpty()) {
				
//						loginToSakai();
//						securityService.pushAdvisor(yesMan);						
						NWULessonGradesManager lessonGradeManger = new NWULessonGradesManager();
						List<NWUStudentEnrollment>  students = nwuCourseEnrollDAO.getEnrollmentsByCourseIdAndDate(course.getCourseId(), previousFireTime);
						
						for (NWUStudentEnrollment stud : students) {					
							
							//loop students for id and course							
							
						}
				}
			}
		}
		}


	/**
	 * Sakai admin login
	 */
	private void loginToSakai() {
		User user = null;
		String userId = serverConfigurationService.getString("integration.admin.username", "adminnwu");
		try {
			user = userDirectoryService.getUserByEid(userId);
			if (user != null) {
				Session sakaiSession = sessionManager.getCurrentSession();
				sakaiSession.setUserId(user.getId());
				sakaiSession.setUserEid(user.getEid());

				// update the user's externally provided realm definitions
				authzGroupService.refreshUser(user.getId());
			}
		} catch (UserNotDefinedException e) {
			// user doesn't exist, lets make it:
			try {
				String password = serverConfigurationService.getString("integration.admin.password", "adminNWU123!@#");
				String email = serverConfigurationService.getString("integration.admin.email", "nwuAdmin@nwu.ac.za");

				user = userDirectoryService.addUser(userId, userId, userId, userId, email, password, "registered",
						null);

			} catch (UserIdInvalidException e1) {
				log.error("Admin userId UserIdInvalidException, userId: " + userId, e1);
			} catch (UserAlreadyDefinedException e1) {
				log.error("Admin userId UserAlreadyDefinedException, userId: " + userId, e1);
			} catch (UserPermissionException e1) {
				log.error("Admin userId UserPermissionException, userId: " + userId, e1);
			}
		}
	}

	/**
	 * Sakai admin logout
	 */
	private void logoutFromSakai() {
		Session sakaiSession = sessionManager.getCurrentSession();
		sakaiSession.invalidate();
	}

	/**
	 * Helper method to create Sakai course site title value
	 * 
	 * @param course
	 * @return siteName
	 */
	private String generateSiteName(NWUCourse course) {
		StringBuilder siteName = new StringBuilder();
		try {
			siteName.append(course.getCourseCode()).append(HYPHEN).append(course.getTermStartDate().getYear());
		} catch (Exception e) {
			log.warn("Could not create site name generateSiteName() for course: " + course);
			return null;
		}
		return siteName.toString();
	}

	public NWUCourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(NWUCourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public NWUCourseEnrollmentDao getEnrollmentDao() {
		return enrollmentDao;
	}

	public void setEnrollmentDao(NWUCourseEnrollmentDao enrollmentDao) {
		this.enrollmentDao = enrollmentDao;
	}

	public NWUCourseLecturerDao getLecturerDao() {
		return lecturerDao;
	}

	public void setLecturerDao(NWUCourseLecturerDao lecturerDao) {
		this.lecturerDao = lecturerDao;
	}	

	public NWUCourseLessonDao getLessonDao() {
		return lessonDao;
	}

	public void setLessonDao(NWUCourseLessonDao lessonDao) {
		this.lessonDao = lessonDao;
	}
	
	public NWULessonGradeDao getLessonGradeDao() {
		return lessonGradeDao;
	}

	public void setLessonGradeDao(NWULessonGradeDao lessonGradeDao) {
		this.lessonGradeDao = lessonGradeDao;
	}


	public UserDirectoryService getUserDirectoryService() {
		return userDirectoryService;
	}

	public void setUserDirectoryService(UserDirectoryService userDirectoryService) {
		this.userDirectoryService = userDirectoryService;
	}

	public ServerConfigurationService getServerConfigurationService() {
		return serverConfigurationService;
	}

	public void setServerConfigurationService(ServerConfigurationService serverConfigurationService) {
		this.serverConfigurationService = serverConfigurationService;
	}

	public AuthzGroupService getAuthzGroupService() {
		return authzGroupService;
	}

	public void setAuthzGroupService(AuthzGroupService authzGroupService) {
		this.authzGroupService = authzGroupService;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public SiteService getSiteService() {
		return siteService;
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public CourseManagementService getCmService() {
		return cmService;
	}

	public void setCmService(CourseManagementService cmService) {
		this.cmService = cmService;
	}

	public CourseManagementAdministration getCmAdmin() {
		return cmAdmin;
	}

	public void setCmAdmin(CourseManagementAdministration cmAdmin) {
		this.cmAdmin = cmAdmin;
	}

	public IdManager getIdManager() {
		return idManager;
	}

	public void setIdManager(IdManager idManager) {
		this.idManager = idManager;
	}

	public GradebookService getGradebookService() {
		return gradebookService;
	}

	public void setGradebookService(GradebookService gradebookService) {
		this.gradebookService = gradebookService;
	}
}
