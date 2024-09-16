package za.ac.nwu.impl.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionException;
import org.sakaiproject.authz.api.AuthzGroupService;
import org.sakaiproject.authz.api.SecurityAdvisor;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.coursemanagement.api.CourseManagementAdministration;
import org.sakaiproject.coursemanagement.api.CourseManagementService;
import org.sakaiproject.coursemanagement.api.exception.IdNotFoundException;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;
import org.sakaiproject.exception.IdInvalidException;
import org.sakaiproject.exception.IdUsedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.id.api.IdManager;
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
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWULecturer;
import za.ac.nwu.api.model.NWUStudentEnrollment;
import za.ac.nwu.api.service.NWUService;
import za.ac.nwu.cm.util.NWUCourseManager;

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

	private ApplicationContext applicationContext;

	private NWUCourseDao courseDao;
	private NWUCourseEnrollmentDao enrollmentDao;
	private NWUCourseLecturerDao lecturerDao;

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
		List<NWUCourse> courses = courseDao.getAllCoursesByYearAndSiteId(Calendar.getInstance().get(Calendar.YEAR));

		if (!courses.isEmpty()) {

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
						serverConfigurationService);
				
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

//		updateNWUeFundiCourseSites();
	}

//	private void updateCourse(NWUCourse clientCourse) {
//		try {
//			courseDao.updateCourse(clientCourse);
//			log.info("Course updated: " + clientCourse);
//		} catch (Exception e) {
//			log.error("updateCourse Exception" + e.getMessage(), e);
//		}
//	}

	/**
	 * Updating NWU Course Sites from CM data
	 */
	public void updateNWUeFundiCourseSites() {

		// Get all existing course from Database for current year and no Sakai site id
		List<NWUCourse> courses = courseDao.getAllCoursesByYearAndSiteId(Calendar.getInstance().get(Calendar.YEAR));

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

		Calendar cal = Calendar.getInstance();
		int year = serverConfigurationService.getInt("nwu.cm.year", 0);
		year = year != 0 ? year : cal.get(Calendar.YEAR);
		List<NWUCourse> courses = courseDao.getAllCoursesByYear(year);// Also by Status, add Status column, if empty
																		// process, otherwise skip. after insert update
																		// to inserted or whatever. modifiedDate?
		if (!courses.isEmpty()) {

			try {

				loginToSakai();
//				securityService.pushAdvisor(yesMan);

				NWUCourseManager courseManager = new NWUCourseManager(cmAdmin, cmService, userDirectoryService,
						serverConfigurationService);
				
				for (NWUCourse course : courses) {

					NWULecturer lecturer = course.getLecturer();
					if (lecturer == null) {
						log.error("Course must have an Instuctor: " + course);
						continue;
					}

					// Manage Course Enrollment
					List<NWUStudentEnrollment> enrollmentList = enrollmentDao
							.getEnrollmentsByCourseIdAndDate(course.getId(), previousFireTime);
					if (enrollmentList != null && !enrollmentList.isEmpty()) {
						courseManager.updateCourseEnrollment(course, enrollmentList);
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

		Calendar cal = Calendar.getInstance();
		int year = serverConfigurationService.getInt("nwu.cm.year", 0);
		year = year != 0 ? year : cal.get(Calendar.YEAR);
		List<NWUCourse> courses = courseDao.getAllCoursesByYear(year);// Also by Status, add Status column, if empty
																		// process, otherwise skip. after insert update
																		// to inserted or whatever. modifiedDate?
		if (!courses.isEmpty()) {

			try {

				loginToSakai();
//				securityService.pushAdvisor(yesMan);

				NWUCourseManager courseManager = new NWUCourseManager(cmAdmin, cmService, userDirectoryService,
						serverConfigurationService);
				
				for (NWUCourse course : courses) {

					NWULecturer lecturer = course.getLecturer();
					if (lecturer == null) {
						log.error("Course must have an Instuctor: " + course);
						continue;
					}

					// Manage Course Lecturers
					List<NWULecturer> lecturers = lecturerDao.getLecturersByCourseIdAndDate(course.getId(),
							previousFireTime);
					if (lecturers != null && !lecturers.isEmpty()) {
						courseManager.updateCourseLecturers(course, lecturers);
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
	 * Create eFundi course site
	 * 
	 * @param course
	 * @return
	 */
	private Site createEFundiCoureSite(NWUCourse course) {

		log.info("NWU - Creating unpublished eFundi Course Site");
		Site newSite = null;
		try {
//			String siteId = siteName.replace(BLANK_SPACE, HYPHEN);
			String siteId = idManager.createUuid();
			if (!siteService.siteExists(siteId)) {
				String description = course.getCourseDescr();
				newSite = siteService.addSite(siteId, "course");
				newSite.setTitle(generateSiteName(course.getYear(), course.getCourseCode()));
				newSite.setDescription(description);
				newSite.setShortDescription(description);
				newSite.setPublished(false);
				newSite.setJoinable(false);

				addDefaultSakaiTools(newSite);

				String term = course.getTerm();
				String termEid = course.getTerm();

				ResourcePropertiesEdit propEdit = newSite.getPropertiesEdit();
				propEdit.addProperty("term", term);
				propEdit.addProperty("term_eid", termEid);
				siteService.save(newSite);

				// Create Academic session if not found
				try {
					cmService.getAcademicSession(termEid);
//					log.info("Found AcademicSession with id " + termEid);
				} catch (IdNotFoundException e) {
//					cmAdmin.createAcademicSession(termEid, term, generateTermDescription(course), null, null);
//					log.info("Created AcademicSession with id " + termEid);					

					log.info("IdNotFoundException AcademicSession with id " + termEid);
				}

				course.setEfundiSiteId(newSite.getId());
				courseDao.updateCourse(course);

				log.info("NWU - New Course site created: " + siteId);
			}

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

				// String id, String eid, String firstName, String lastName, String email,
				// String pw, String type, ResourceProperties properties
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
	 * Helper method to create Sakai course site term value
	 * 
	 * @param course
	 * @return
	 */
//	private String generateTerm(NWUCourse course) {
//		StringBuilder term = new StringBuilder();
//		term.append(SEMESTER_SEM).append(BLANK_SPACE).append(course.getSemCode()).append(BLANK_SPACE);
//		int yearLastTwoDigits = course.getYear() % 100;
//		term.append("" + (yearLastTwoDigits - 1)).append("/" + yearLastTwoDigits);
//		return term.toString();
//	}
//
//	/**
//	 * Helper method to create Sakai course site term_eid value
//	 * 
//	 * @param course
//	 * @return
//	 */
//	private String generateTermEid(NWUCourse course) {
//		StringBuilder termEid = new StringBuilder();
//		termEid.append(SEMESTER_S).append(course.getSemCode()).append(HYPHEN);
//		int yearLastTwoDigits = course.getYear() % 100;
//		termEid.append("" + (yearLastTwoDigits - 1)).append("" + yearLastTwoDigits);
//		return termEid.toString();
//	}
//	
//	/**
//	 * Helper method to create Sakai course site term description value
//	 * 
//	 * @param course
//	 * @return
//	 */
//	private String generateTermDescription(NWUCourse course) {
//		StringBuilder term = new StringBuilder();
//		term.append(course.getSemester()).append(BLANK_SPACE);
//		int yearLastTwoDigits = course.getYear() % 100;
//		term.append("" + (yearLastTwoDigits - 1)).append("/" + yearLastTwoDigits);
//		return term.toString();
//	}

	/**
	 * Helper method to create Sakai course site description value
	 * 
	 * @param course
	 * @return
	 */
//	private String generateCourseDescription(NWUCourse course) {
//		StringBuilder description = new StringBuilder();
//		description.append(course.getCourseCode()).append(BLANK_SPACE);
//		description.append(course.getCourse()).append(BLANK_SPACE);
//		description.append(course.getCampus()).append(BLANK_SPACE);
//		description.append(course.getSemester()).append(BLANK_SPACE);
//		description.append("" + course.getYear());
//		return description.toString();
//	}
//
//	/**
//	 * Helped method to get Sakai site Id
//	 * 
//	 * @param enrollment
//	 * @return
//	 */
//	private String generateSiteNameId(NWUEnrollment enrollment) {
//		return generateSiteName(enrollment).replace(BLANK_SPACE, HYPHEN);
//	}
//
//	/**
//	 * Helped method to get Sakai site Id
//	 * 
//	 * @param lecturer
//	 * @return
//	 */
//	private String generateSiteNameId(NWULecturer lecturer) {
//		return generateSiteName(lecturer).replace(BLANK_SPACE, HYPHEN);
//	}

//	/**
//	 * Helper method to create Sakai course site title value
//	 * 
//	 * @param enrollment
//	 * @return
//	 */
//	private String generateSiteName(NWUEnrollment enrollment) {
//		return generateSiteName(enrollment.getYear(), enrollment.getCourseCode(), enrollment.getCampusCode(),
//				enrollment.getSemCode());
//	}
//
//	/**
//	 * Helper method to create Sakai course site title value
//	 * 
//	 * @param lecturer
//	 * @return
//	 */
//	private String generateSiteName(NWULecturer lecturer) {
//		return generateSiteName(lecturer.getYear(), lecturer.getCourse(), lecturer.getOfferingType(),
//				lecturer.getSemester());
//	}

	/**
	 * Helper method to create Sakai course site title value
	 * 
	 * @param year
	 * @param courseCode
	 * @param campusCode
	 * @param semCode
	 * @return
	 */
//	private String generateSiteName(int year, String courseCode, String campusCode, String semCode) {
//		StringBuilder siteid = new StringBuilder();
//		try {
//			String courseSplitArray[] = courseCode.split("(?<=\\D)(?=\\d)");
//			String campusCodeVal = campusCode.startsWith("0") ? campusCode.substring(1) : campusCode;
//			int yearLastTwoDigits = year % 100;
//
//			siteid.append(courseSplitArray[0]).append(BLANK_SPACE).append(courseSplitArray[1]).append(BLANK_SPACE);
//			siteid.append(campusCodeVal).append(BLANK_SPACE);
//			siteid.append(SEMESTER_S).append(semCode).append(HYPHEN);
//			siteid.append("" + (yearLastTwoDigits - 1)).append("" + yearLastTwoDigits);
//		} catch (Exception e) {
//			log.warn("Could not create siteId generateSiteId() for : year:" + year + ", courseCode: "
//					+ courseCode + ", campusCode: " + campusCode + ", semCode: " + semCode);
//			return null;
//		}
//		return siteid.toString();
//	}
	private String generateSiteName(int year, String courseCode) {
		StringBuilder siteId = new StringBuilder();
		try {
			siteId.append(courseCode).append(HYPHEN).append("" + year);
		} catch (Exception e) {
			log.warn("Could not create siteId generateSiteId() for : year:" + year + ", courseCode: " + courseCode);
			return null;
		}
		return siteId.toString();
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
}
