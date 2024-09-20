package za.ac.nwu.cm.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.coursemanagement.api.AcademicSession;
import org.sakaiproject.coursemanagement.api.CourseManagementAdministration;
import org.sakaiproject.coursemanagement.api.CourseManagementService;
import org.sakaiproject.coursemanagement.api.CourseOffering;
import org.sakaiproject.coursemanagement.api.EnrollmentSet;
import org.sakaiproject.coursemanagement.api.SectionCategory;
import org.sakaiproject.coursemanagement.api.exception.IdNotFoundException;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserNotDefinedException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWULecturer;
import za.ac.nwu.api.model.NWUStudentEnrollment;

/**
 * This Manager creates and removes Course Management data in eFundi data.
 * 
 * @author JC Gillman
 * 
 */
@Slf4j
public class NWUCourseManager {

	private CourseManagementAdministration cmAdmin;

	private CourseManagementService cmService;

	private UserDirectoryService userDirectoryService;

	private ServerConfigurationService serverConfigurationService;	

	private SiteService siteService;

	private String status = null;
	private String enrollmentSetCategory = null;
	private String enrollmentSetCredits = null;
	private String enrollmentStatus = null;
	private String enrollmentCredits = null;
	private String gradingScheme = null;
	private String sectionCategoryCode = null;
	private String sectionStatus = null;
	private String sectionLecturerRole = null;
	private String sectionStudentRole = null;
	private String courseSetCategory = null;
	private String sectionCategory = null;
	private String sectionCategoryDesc = null;

	private static final String REGISTERED = "Registered";
	private static final String WITHDRAWN = "Withdrawn";

	public NWUCourseManager(final CourseManagementAdministration cmAdmin, final CourseManagementService cmService,
			final UserDirectoryService userDirectoryService,
			final ServerConfigurationService serverConfigurationService, final SiteService siteService) {

		this.cmAdmin = cmAdmin;
		this.cmService = cmService;
		this.userDirectoryService = userDirectoryService;
		this.serverConfigurationService = serverConfigurationService;
		this.siteService = siteService;

		this.status = serverConfigurationService.getString("nwu.cm.CourseOffering.status", "Active");
		this.enrollmentSetCategory = serverConfigurationService.getString("nwu.cm.EnrollmentSet.category", "category");
		this.enrollmentSetCredits = serverConfigurationService.getString("nwu.cm.EnrollmentSet.credits", "0");
		this.enrollmentStatus = serverConfigurationService.getString("nwu.cm.Enrollment.status", "enrolled");
		this.enrollmentCredits = serverConfigurationService.getString("nwu.cm.Enrollment.credits", "0");
		this.gradingScheme = serverConfigurationService.getString("nwu.cm.Enrollment.gradingScheme", "standard");

		this.sectionCategory = serverConfigurationService.getString("nwu.cm.SectionCategory.category", "LCT");
		this.sectionCategoryDesc = serverConfigurationService.getString("nwu.cm.SectionCategory.description",
				"Lecture");
		
		this.sectionCategoryCode = createSectionCategory();
		this.sectionStatus = serverConfigurationService.getString("nwu.cm.Section.Membership.status", "Active");
		this.sectionLecturerRole = serverConfigurationService.getString("nwu.cm.Section.lecturer.role", "I");
		this.sectionStudentRole = serverConfigurationService.getString("nwu.cm.Section.student.role", "S");
		this.courseSetCategory = this.serverConfigurationService.getString("nwu.cm.CourseSet.category", "category");		
	}

	public void createCourseManagement(NWUCourse course) {
//        final Set<Module> modules = dataManager.getModules(year, true, Status.INSERTED);
		final AcademicSession academicSession = createAcademicSession(course);
		createCourseSet(course);
		createCanonicalCourse(Utility.getCanonicalCourseReference(course));
		createCourseOfferingRelatedData(academicSession, course);
		// Insert/Update students/lecturers
        if (serverConfigurationService.getBoolean("nwu.cm.users.create", false)) {
            LDAPRetrieval ldap = Utility.getLDAPRetrieval(serverConfigurationService);
            try {
            	Set<RosterUser> lecturers = new HashSet<RosterUser>();
            	lecturers.add(new RosterUser("" + course.getLecturer().getInstructorNumber()));            	
                ldap.setLecturerDetails(lecturers);
                createLecturerSakaiUsers(lecturers);           
                
                Set<RosterUser> students = new HashSet<RosterUser>();
                if (students != null && !students.isEmpty()) {
                	for (NWUStudentEnrollment student : course.getStudents()) {
                        students.add(new RosterUser("" + student.getNwuNumber()));
                    }
                    ldap.setStudentDetails(students);
                    createStudentSakaiUsers(students);
                }
            }
            catch (Exception e) {
                log.error("Could not create Sakai Users for Course Management. See previous log entries for more details.");
            }
            finally {
                try {
                    ldap.getContext().close();
                }
                catch (NamingException e) {
                	log.warn("Error closing LDAPRetrieval Context", e);
                }
            }
        }
	}

	/**
	 * Create the AcademicSession and set it active/current
	 */
	private AcademicSession createAcademicSession(NWUCourse course) {

//        Calendar start = Calendar.getInstance();
//        start.set(year, Calendar.JANUARY, 1);
//        Calendar end = Calendar.getInstance();
//        end.set(year, Calendar.DECEMBER, 31);
//        String title = MessageFormat.format(serverConfigurationService.getString(
//            "nwu.cm.AcademicSession.title", "Year {0,number,####}"), year);
//        String description = MessageFormat.format(serverConfigurationService.getString(
//            "nwu.cm.AcademicSession.description", "Academic Session for Year {0,number,####}"),
//            year);
		AcademicSession academicSession = null;
		try {
			academicSession = cmService.getAcademicSession(course.getTerm());
			log.info("Retrieved AcademicSession with id " + course.getTerm());
		} catch (IdNotFoundException e) {
			// If AcademicSession do not exist, create it.
//            academicSession = cmAdmin.createAcademicSession(course.getTerm(), course.getTerm(), description,
//                start.getTime(), end.getTime());
			academicSession = cmAdmin.createAcademicSession(course.getTerm(), course.getTerm(),
					"Academic Session for " + course.getTerm(), null, null);
			log.info("Created AcademicSession with id " + course.getTerm());
		}
		List<String> acadSessionIds = new ArrayList<String>();
		List<AcademicSession> allAcadSessions = cmService.getAcademicSessions();
		for (AcademicSession availableAcadSession : allAcadSessions) {
			acadSessionIds.add(availableAcadSession.getEid());
		}
		cmAdmin.setCurrentAcademicSessions(acadSessionIds);
		return academicSession;
	}

	/**
	 * Create Course Set the subject code. These are shown in the 'Subject' droplist
	 * of Sakai's course site setup.
	 */
	private void createCourseSet(NWUCourse course) {
		String courseSetId = Utility.getCourseSet(course);
		if (!cmService.isCourseSetDefined(courseSetId)) {
			cmAdmin.createCourseSet(courseSetId, courseSetId, courseSetId, this.courseSetCategory, null);
			log.info("Inserted CourseSet with id " + courseSetId);
		}
	}

	/**
	 * These abstract records are not shown on screen.
	 */
	private void createCanonicalCourse(String canonicalCourseId) {
		if (!cmService.isCanonicalCourseDefined(canonicalCourseId)) {
			cmAdmin.createCanonicalCourse(canonicalCourseId, canonicalCourseId, canonicalCourseId);
			log.info("Inserted CanonicalCourse with id " + canonicalCourseId);
		}
	}

	/**
	 * The Course Offering records are shown in the 'Course' droplist of Sakai's
	 * course site setup.
	 */
	private void createCourseOfferingRelatedData(final AcademicSession academicSession, final NWUCourse course) {

		String courseSetId = Utility.getCourseSet(course);
		String courseOfferingReference = Utility.getCourseOfferingReference(course);
		String canonicalCourseReference = Utility.getCanonicalCourseReference(course);
		String enrollmentSetReference = Utility.getEnrollmentSetReference(course);

		if (!cmService.isCourseOfferingDefined(courseOfferingReference)) {
			// Give Canonical Course's Eid as title for Course Offering
			cmAdmin.createCourseOffering(courseOfferingReference, canonicalCourseReference, courseOfferingReference,
					status, academicSession.getEid(), canonicalCourseReference, academicSession.getStartDate(),
					academicSession.getEndDate());
			log.info("Inserted CourseOffering with id " + courseOfferingReference);
		}
		addCourseOfferingsToCourseSets(courseOfferingReference, courseSetId);
		// Add EnrollmentSets
		createEnrollmentSets(course);
		// Add Enrollments
		createEnrollment(enrollmentSetReference, course);
		// Add Sections
		createSections(course);
	}

	private void addCourseOfferingsToCourseSets(final String courseOfferingId, final String courseSetId) {
		boolean linkExists = false;
		// Check if Offering is already linked to CourseSet
		Set<CourseOffering> linkedOfferings = cmService.getCourseOfferingsInCourseSet(courseSetId);
		for (CourseOffering linkedOffering : linkedOfferings) {
			if (Utility.equals(linkedOffering.getEid(), courseOfferingId)) {
				log.info("CourseOffering with id '" + courseOfferingId + "' is already linked to CourseSet with id '"
						+ courseSetId + "'.");
				linkExists = true;
				break;
			}
		}
		if (!linkExists) {
			// Add if Offering is not already linked.
			cmAdmin.addCourseOfferingToCourseSet(courseSetId, courseOfferingId);
			log.info("Added CourseOffering (" + courseOfferingId + ") to CourseSet (" + courseSetId + ")");
		}
	}

	/**
	 * Arbitrary section category title.
	 */
	private String createSectionCategory() {
		String sectionCategoryCode = null;
		boolean exists = false;
		for (String categoryCode : cmService.getSectionCategories()) {
			if (sectionCategory.equals(categoryCode)) {
				if (sectionCategoryDesc.equals(cmService.getSectionCategoryDescription(sectionCategory))) {
					sectionCategoryCode = categoryCode;
					log.info("Section Category '" + sectionCategory + "' already exists.");
					exists = true;
					break;
				}
			}
		}
		if (!exists) {
			SectionCategory sectionCategory = cmAdmin.addSectionCategory(this.sectionCategory, sectionCategoryDesc);
			sectionCategoryCode = sectionCategory.getCategoryCode();
			log.info("Section Category (" + sectionCategory + " - " + sectionCategoryDesc + ") successfully inserted.");
		}
		return sectionCategoryCode;
	}

	/**
	 * Creates the EnrollmentSets per CourseOffering/Class Group.
	 */
	private void createEnrollmentSets(final NWUCourse course) {

		String courseOfferingReference = Utility.getCourseOfferingReference(course);
		String enrollmentSetReference = Utility.getEnrollmentSetReference(course);

		if (!cmService.isEnrollmentSetDefined(enrollmentSetReference)) {

			Set<String> lecturerUserNames = new HashSet<String>();
			lecturerUserNames.add("" + course.getLecturer().getInstructorNumber());

			cmAdmin.createEnrollmentSet(enrollmentSetReference, enrollmentSetReference, enrollmentSetReference,
					enrollmentSetCategory, enrollmentSetCredits, courseOfferingReference, lecturerUserNames);

			log.info("Inserted EnrollmentSet with id " + enrollmentSetReference);
		}
	}


    /**
     * Only students should have Enrollments. Lecturers are added to the EnrollmentSet.
     */
    private void createEnrollment(final String enrollmentSetId, final NWUCourse course) {
        for (String studentUserName : Utility.getStudentUserNames(course)) {
            cmAdmin.addOrUpdateEnrollment(studentUserName, enrollmentSetId, enrollmentStatus,
                enrollmentCredits, gradingScheme);
            log.info("Added/Updated Enrollment for user id " + studentUserName);
        }
    }

	/**
	 * Only students should have Enrollments. Lecturers are added to the EnrollmentSet.
	 */
	public void updateCourseEnrollment(NWUCourse course, List<NWUStudentEnrollment> enrollmentList) {

		String courseOfferingReference = Utility.getCourseOfferingReference(course);
		String enrollmentSetReference = Utility.getEnrollmentSetReference(course);		

        log.info("updateCourseEnrollment.courseOfferingReference " + courseOfferingReference);
        log.info("updateCourseEnrollment.enrollmentSetReference " + enrollmentSetReference);

		Set<RosterUser> students = new HashSet<RosterUser>();
		for (Iterator iterator = enrollmentList.iterator(); iterator.hasNext();) {
			NWUStudentEnrollment studentEnrollment = (NWUStudentEnrollment) iterator.next();
			if (studentEnrollment.getStudentStatus().equals(REGISTERED)) {
				// Enrollment
				cmAdmin.addOrUpdateEnrollment("" + studentEnrollment.getNwuNumber(), enrollmentSetReference,
						enrollmentStatus, enrollmentCredits, gradingScheme);
				log.info("Added Enrollment for user id " + studentEnrollment.getNwuNumber());
				// Section Memberships
				cmAdmin.addOrUpdateSectionMembership("" + studentEnrollment.getNwuNumber(), sectionStudentRole,
						courseOfferingReference, sectionStatus);
				log.info("Added SectionMembership - " + studentEnrollment.getNwuNumber() + " to "
						+ courseOfferingReference);
				
				students.add(new RosterUser("" + studentEnrollment.getNwuNumber()));
			}

			if (studentEnrollment.getStudentStatus().equals(WITHDRAWN)) {
				// Section Memberships
				cmAdmin.removeSectionMembership("" + studentEnrollment.getNwuNumber(), courseOfferingReference);
				log.info("Removed Student Membership from Section: " + studentEnrollment.getNwuNumber() + " - "
						+ courseOfferingReference);
				// Enrollment
				cmAdmin.removeEnrollment("" + studentEnrollment.getNwuNumber(), enrollmentSetReference);
				log.info("Removed Student from Enrollment: " + studentEnrollment.getNwuNumber() + " - " + enrollmentSetReference);
			}
		}		

        if (serverConfigurationService.getBoolean("nwu.cm.users.create", false) && !students.isEmpty()) {
            LDAPRetrieval ldap = Utility.getLDAPRetrieval(serverConfigurationService);
            try {
                ldap.setStudentDetails(students);
                createStudentSakaiUsers(students);
            }
            catch (Exception e) {
                log.error("Could not create Sakai Users for Course Management. See previous log entries for more details.");
            }
            finally {
                try {
                    ldap.getContext().close();
                }
                catch (NamingException e) {
                	log.warn("Error closing LDAPRetrieval Context", e);
                }
            }
        }
	}

	/**
	 * Updating Lecturers data for courses
	 */
	public void updateCourseLecturers(NWUCourse course, List<NWULecturer> lecturers) {
		
		String enrollmentSetReference = Utility.getEnrollmentSetReference(course);

		log.info("updateCourseLecturers.enrollmentSetReference " + enrollmentSetReference);
		
		if (cmService.isEnrollmentSetDefined(enrollmentSetReference)) {
            EnrollmentSet enrollmentSet = cmService.getEnrollmentSet(enrollmentSetReference);
            Set<String> instructors = enrollmentSet.getOfficialInstructors();

    		log.info("updateCourseLecturers.getOfficialInstructors " + instructors);
    		
//            Set<String> lecturerUserNames = new HashSet<String>();
			NWULecturer lecturer = course.getLecturer();
			if (lecturer != null && !instructors.contains("" + lecturer.getInstructorNumber())) {
				
				List<String> oldInstructors = new ArrayList<String>(instructors); 
				try {
		    		log.info("updateCourseLecturers.siteService.getSite " + course.getEfundiSiteId());
		    		
					Site site = siteService.getSite(course.getEfundiSiteId());
		    		
					instructors.clear();
					instructors.add("" + lecturer.getInstructorNumber());				

		            enrollmentSet.setOfficialInstructors(instructors);
		            cmAdmin.updateEnrollmentSet(enrollmentSet);
		            log.info("Updated Lecturer from EnrollmentSet: "
		                    + lecturer.getInstructorNumber()
		                    + " - "
		                    + enrollmentSetReference);
					
		            // add new lecturer user as the maintainer
		            site.addMember("" + course.getLecturer().getInstructorNumber(), site.getMaintainRole(), true, false);
		    		log.info("updateCourseLecturers.site.addMember " + course.getLecturer().getInstructorNumber());
					
		            // remove old instructor
		            site.removeMember(oldInstructors.get(0));
		            log.info("updateCourseLecturers.site.removeMember " + oldInstructors.get(0));
		            
					User user = userDirectoryService.getUserByEid("" + course.getLecturer().getInstructorNumber());

					ResourcePropertiesEdit propEdit = site.getPropertiesEdit();
					propEdit.addProperty(Site.PROP_SITE_CONTACT_NAME, user.getDisplayName());
					propEdit.addProperty(Site.PROP_SITE_CONTACT_EMAIL, user.getEmail());
					
		            log.info("updateCourseLecturers.userDirectoryService.getUserByEid " + user.getEid());

					siteService.save(site);					
					
				} catch (IdUnusedException e1) {
                    log.error("Could not find Site for Id: " + course.getEfundiSiteId());
				} catch (UserNotDefinedException e) {
                    log.error("Could not find User for Id: " + course.getLecturer().getInstructorNumber());
				} catch (PermissionException e) {
                    log.error("Could save Site with Id: " + course.getEfundiSiteId());
				}
	            
	            if (serverConfigurationService.getBoolean("nwu.cm.users.create", false)) {
	                LDAPRetrieval ldap = Utility.getLDAPRetrieval(serverConfigurationService);
	                try {
	                	Set<RosterUser> rosterUsers = new HashSet<RosterUser>();
	                	rosterUsers.add(new RosterUser("" + lecturer.getInstructorNumber()));            	
	                    ldap.setLecturerDetails(rosterUsers);
	                    createLecturerSakaiUsers(rosterUsers);
	                }
	                catch (Exception e) {
	                    log.error("Could not create Sakai Users for Course Management. See previous log entries for more details.");
	                }
	                finally {
	                    try {
	                        ldap.getContext().close();
	                    }
	                    catch (NamingException e) {
	                    	log.warn("Error closing LDAPRetrieval Context", e);
	                    }
	                }
	            }
			}			
        }
	}

	/**
	 * The Section's description is displayed (with checkbox) on the Course/Section
	 * Information screen of Sakai's course site setup.
	 */
	private void createSections(final NWUCourse course) {

		String courseOfferingReference = Utility.getCourseOfferingReference(course);
		String enrollmentSetReference = Utility.getEnrollmentSetReference(course);

		// Section's make use of the same eids as CourseOfferings.
		if (!cmService.isSectionDefined(courseOfferingReference)) {
			cmAdmin.createSection(courseOfferingReference, courseOfferingReference,
					courseOfferingReference + " Lecture", sectionCategoryCode, null, courseOfferingReference,
					enrollmentSetReference);
			log.info("Inserted Section with id " + courseOfferingReference);
		}
		// Add Section Memberships
		createSectionMemberships(course, sectionStatus, sectionLecturerRole, sectionStudentRole);
	}

	private void createSectionMemberships(final NWUCourse course, final String sectionStatus,
			final String sectionLecturerRole, final String sectionStudentRole) {

		String courseOfferingReference = Utility.getCourseOfferingReference(course);

		// Students
        for (NWUStudentEnrollment student : course.getStudents()) {
            cmAdmin.addOrUpdateSectionMembership("" + student.getNwuNumber(), sectionStudentRole,
            		courseOfferingReference, sectionStatus);
            log.info("Added/Updated SectionMembership - "
                    + student.getNwuNumber()
                    + " to "
                    + courseOfferingReference);
        }
	}

    private void createLecturerSakaiUsers(Set<RosterUser> lecturers) {
        try {
            for (RosterUser lecturer : lecturers) {
                String lecturerUserId = null;
                try {
                    lecturerUserId = userDirectoryService.getUserId(lecturer.getUserName());
                }
                catch (Exception e1) {
                    lecturerUserId = "";
                }
                boolean lecturerExists = lecturerUserId != null && !lecturerUserId.equals("");
                if (lecturerExists) {
                    log.info("Lecturer " + lecturer.getUserName() + " already exists.");
                }
                else {
                    userDirectoryService.addUser(null, lecturer.getUserName(),
                        lecturer.getFirstName(), lecturer.getSurname(), lecturer.getEmail(),
                        lecturer.getPassword(), "maintain", null);
                    log.info("Added user " + lecturer.getUserName());
                }
            }
        }
        catch (Exception e2) {
            log.error("NWUCourseManager - createLecturerSakaiUsers - Exception occured: ",
                e2);
        }
    }

    private void createStudentSakaiUsers(Set<RosterUser> students) {
        try {
            for (RosterUser student : students) {
                String studentUserId = null;
                try {
                    studentUserId = userDirectoryService.getUserId(student.getUserName());
                }
                catch (Exception e) {
                    studentUserId = "";
                }
                boolean studentExists = studentUserId != null && !studentUserId.equals("");
                if (studentExists) {
                    log.info("Student " + student.getUserName() + " already exists.");
                }
                else {
                    userDirectoryService.addUser(null, student.getUserName(),
                        student.getFirstName(), student.getSurname(), student.getEmail(),
                        student.getPassword(), "student", null);
                    log.info("Added user " + student.getUserName());
                }
            }
        }
        catch (Exception e2) {
            log.error("NWUCourseManager - createStudentSakaiUsers - Exception occured: ", e2);
        }
    }
}
