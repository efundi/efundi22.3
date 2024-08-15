package za.ac.nwu.cm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.coursemanagement.api.AcademicSession;
import org.sakaiproject.coursemanagement.api.CourseManagementAdministration;
import org.sakaiproject.coursemanagement.api.CourseManagementService;
import org.sakaiproject.coursemanagement.api.CourseOffering;
import org.sakaiproject.coursemanagement.api.SectionCategory;
import org.sakaiproject.coursemanagement.api.exception.IdNotFoundException;
import org.sakaiproject.user.api.UserDirectoryService;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.model.NWUCourse;

/**
 * This Manager creates and removes Course Management data in eFundi
 * data.
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

    public NWUCourseManager(final CourseManagementAdministration cmAdmin, final CourseManagementService cmService,
            final UserDirectoryService userDirectoryService,
            final ServerConfigurationService serverConfigurationService) {
        this.cmAdmin = cmAdmin;
        this.cmService = cmService;
        this.userDirectoryService = userDirectoryService;
        this.serverConfigurationService = serverConfigurationService;
    }

    public void createCourseManagement(NWUCourse course) {
//        final Set<Module> modules = dataManager.getModules(year, true, Status.INSERTED);
        final AcademicSession academicSession = createAcademicSession(course);
        createCourseSet(course);
        createCanonicalCourse(Utility.getCanonicalCourseReference(course));
        createCourseOfferingRelatedData(academicSession, course);
        //Insert/Update students/lecturers
//        if (serverConfigurationService.getBoolean("nwu.cm.users.create", false)) {
//            LDAPRetrieval ldap = Utility.getLDAPRetrieval(serverConfigurationService);
//            try {
//                final Set<Lecturer> lecturers = Utility.getAllLecturers(modules);
//                if (lecturers != null && !lecturers.isEmpty()) {
//                    ldap.setLecturerDetails(lecturers);
//                    createLecturerSakaiUsers(lecturers);
//                }
//                final Set<Student> students = Utility.getAllStudents(modules);
//                if (students != null && !students.isEmpty()) {
//                    ldap.setStudentDetails(students);
//                    createStudentSakaiUsers(students);
//                }
//            }
//            catch (Exception e) {
//                log.error("Could not create Sakai Users for Course Management. See previous log entries for more details.");
//            }
//            finally {
//                try {
//                    ldap.getContext().close();
//                }
//                catch (NamingException e) {
//                	log.warn("Error closing LDAPRetrieval Context", e);
//                }
//            }
//        }
        //Update CM Link records
//        dataManager.updateInsertedDataStatus(year);
        // course.setStatus, return so that it can be committed in method that called this
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
        }
        catch (IdNotFoundException e) {
            //If AcademicSession do not exist, create it.
//            academicSession = cmAdmin.createAcademicSession(course.getTerm(), course.getTerm(), description,
//                start.getTime(), end.getTime());
        	academicSession = cmAdmin.createAcademicSession(course.getTerm(), course.getTerm(), "Academic Session for " + course.getTerm(), null, null);
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
     * Create Course Set the subject code.
     * These are shown in the 'Subject' droplist of Sakai's course site setup.
     */
    private void createCourseSet(NWUCourse course) {
        String category = serverConfigurationService.getString("nwu.cm.CourseSet.category",
            "category");
        String courseSetId = Utility.getCourseSet(course);
        if (!cmService.isCourseSetDefined(courseSetId)) {
            cmAdmin.createCourseSet(courseSetId, courseSetId, courseSetId, category, null);
            log.info("Inserted CourseSet with id " + courseSetId);
        }
    }

    /**
     * These abstract records are not shown on screen.
     */
    private void createCanonicalCourse(String canonicalCourseId) {
    	if (!cmService.isCanonicalCourseDefined(canonicalCourseId)) {
            cmAdmin.createCanonicalCourse(canonicalCourseId, canonicalCourseId,
                canonicalCourseId);
            log.info("Inserted CanonicalCourse with id " + canonicalCourseId);
        }
    }

    /**
     * The Course Offering records are shown in the 'Course' droplist of Sakai's course site setup.
     */
    private void createCourseOfferingRelatedData(final AcademicSession academicSession,
            final NWUCourse course) {
        String status = serverConfigurationService.getString("nwu.cm.CourseOffering.status",
            "Active");
        String enrollmentSetCategory = serverConfigurationService.getString(
            "nwu.cm.EnrollmentSet.category", "category");
        String enrollmentSetCredits = serverConfigurationService.getString(
            "nwu.cm.EnrollmentSet.credits", "0");
        String enrollmentStatus = serverConfigurationService.getString("nwu.cm.Enrollment.status",
            "enrolled");
        String enrollmentCredits = serverConfigurationService.getString(
            "nwu.cm.Enrollment.credits", "0");
        String gradingScheme = serverConfigurationService.getString(
            "nwu.cm.Enrollment.gradingScheme", "standard");
        String sectionCategoryCode = createSectionCategory();
        String sectionStatus = serverConfigurationService.getString(
            "nwu.cm.Section.Membership.status", "Active");
        String sectionLecturerRole = serverConfigurationService.getString(
            "nwu.cm.Section.lecturer.role", "I");
        String sectionStudentRole = serverConfigurationService.getString(
            "nwu.cm.Section.student.role", "S");

        String courseSetId = Utility.getCourseSet(course);
        String courseOfferingReference = Utility.getCourseOfferingReference(course);
        String canonicalCourseReference = Utility.getCanonicalCourseReference(course);
        String enrollmentSetReference = Utility.getEnrollmentSetReference(course);

        if (!cmService.isCourseOfferingDefined(courseOfferingReference)) {
            //Give Canonical Course's Eid as title for Course Offering
            cmAdmin.createCourseOffering(courseOfferingReference,
            		canonicalCourseReference, courseOfferingReference,
                status, academicSession.getEid(), canonicalCourseReference,
                academicSession.getStartDate(), academicSession.getEndDate());
            log.info("Inserted CourseOffering with id " + courseOfferingReference);
        }
        addCourseOfferingsToCourseSets(courseOfferingReference, courseSetId);
        //Add EnrollmentSets
        createEnrollmentSets(course, enrollmentSetCategory, enrollmentSetCredits);
        //Add Enrollments
//        createEnrollments(enrollmentSetReference, module, enrollmentStatus,
//            enrollmentCredits, gradingScheme);
        // NOT adding enrollments here yet?????	
        //Add Sections
        createSections(course, sectionCategoryCode, sectionStatus, sectionLecturerRole,
            sectionStudentRole);
    }

    private void addCourseOfferingsToCourseSets(final String courseOfferingId,
            final String courseSetId) {
        boolean linkExists = false;
        //Check if Offering is already linked to CourseSet
        Set<CourseOffering> linkedOfferings = cmService.getCourseOfferingsInCourseSet(courseSetId);
        for (CourseOffering linkedOffering : linkedOfferings) {
            if (Utility.equals(linkedOffering.getEid(), courseOfferingId)) {
            	log.info("CourseOffering with id '"
                        + courseOfferingId
                        + "' is already linked to CourseSet with id '"
                        + courseSetId
                        + "'.");
                linkExists = true;
                break;
            }
        }
        if (!linkExists) {
            //Add if Offering is not already linked.
            cmAdmin.addCourseOfferingToCourseSet(courseSetId, courseOfferingId);
            log.info("Added CourseOffering ("
                    + courseOfferingId
                    + ") to CourseSet ("
                    + courseSetId
                    + ")");
        }
    }

    /**
     * Arbitrary section category title.
     */
    private String createSectionCategory() {
        String sectionCategoryCode = null;
        String category = serverConfigurationService.getString("nwu.cm.SectionCategory.category",
            "LCT");
        String catDesc = serverConfigurationService.getString("nwu.cm.SectionCategory.description",
            "Lecture");
        boolean exists = false;
        for (String categoryCode : cmService.getSectionCategories()) {
            if (category.equals(categoryCode)) {
                if (catDesc.equals(cmService.getSectionCategoryDescription(category))) {
                    sectionCategoryCode = categoryCode;
                    log.info("Section Category '" + category + "' already exists.");
                    exists = true;
                    break;
                }
            }
        }
        if (!exists) {
            SectionCategory sectionCategory = cmAdmin.addSectionCategory(category, catDesc);
            sectionCategoryCode = sectionCategory.getCategoryCode();
            log.info("Section Category (" + category + " - " + catDesc + ") successfully inserted.");
        }
        return sectionCategoryCode;
    }

    /**
     * Creates the EnrollmentSets per CourseOffering/Class Group.
     */
    private void createEnrollmentSets(final NWUCourse course, final String enrollmentSetCategory,
            final String enrollmentSetCredits) {

        String courseOfferingReference = Utility.getCourseOfferingReference(course);
        String enrollmentSetReference = Utility.getEnrollmentSetReference(course);
        
        if (!cmService.isEnrollmentSetDefined(enrollmentSetReference)) {
            cmAdmin.createEnrollmentSet(enrollmentSetReference,
            		enrollmentSetReference, enrollmentSetReference,
                enrollmentSetCategory, enrollmentSetCredits, courseOfferingReference,
                Utility.getLecturerUserNames(course));
            log.info("Inserted EnrollmentSet with id " + enrollmentSetReference);
        }
    }

    /**
     * Only students should have Enrollments. Lecturers are added to the EnrollmentSet.
     */
//    private void createEnrollments(final String enrollmentSetId, final Module module,
//            final String enrollmentStatus, final String enrollmentCredits,
//            final String gradingScheme) {
//        for (String studentUserName : Utility.getStudentUserNames(module)) {
//            cmAdmin.addOrUpdateEnrollment(studentUserName, enrollmentSetId, enrollmentStatus,
//                enrollmentCredits, gradingScheme);
//            log.info("Added/Updated Enrollment for user id " + studentUserName);
//        }
//    }

    /**
     * The Section's description is displayed (with checkbox) on the Course/Section Information screen of Sakai's course site setup.
     */
    private void createSections(final NWUCourse course, final String sectionCategoryCode,
            final String sectionStatus, final String sectionLecturerRole,
            final String sectionStudentRole) {

        String courseOfferingReference = Utility.getCourseOfferingReference(course);
        String enrollmentSetReference = Utility.getEnrollmentSetReference(course);
        
        //Section's make use of the same eids as CourseOfferings.
        if (!cmService.isSectionDefined(courseOfferingReference)) {
            cmAdmin.createSection(courseOfferingReference,
            		courseOfferingReference, courseOfferingReference
                        + " Lecture", sectionCategoryCode, null,
                        courseOfferingReference, enrollmentSetReference);
            log.info("Inserted Section with id " + courseOfferingReference);
        }
        //Add Section Memberships
        createSectionMemberships(course, sectionStatus, sectionLecturerRole, sectionStudentRole);
    }

    private void createSectionMemberships(final NWUCourse course, final String sectionStatus,
            final String sectionLecturerRole, final String sectionStudentRole) {
    	
        String courseOfferingReference = Utility.getCourseOfferingReference(course);
        
        //Students
//        for (Student student : module.getLinkedStudents()) {
//            cmAdmin.addOrUpdateSectionMembership(student.getUserName(), sectionStudentRole,
//            		courseOfferingReference, sectionStatus);
//            log.info("Added/Updated SectionMembership - "
//                    + student.getUserName()
//                    + " to "
//                    + courseOfferingReference);
//        }
    }

//    private void createLecturerSakaiUsers(Set<Lecturer> lecturers) {
//        try {
//            for (Lecturer lecturer : lecturers) {
//                String lecturerUserId = null;
//                try {
//                    lecturerUserId = userDirectoryService.getUserId(lecturer.getUserName());
//                }
//                catch (Exception e1) {
//                    lecturerUserId = "";
//                }
//                boolean lecturerExists = lecturerUserId != null && !lecturerUserId.equals("");
//                if (lecturerExists) {
//                    log.info("Lecturer " + lecturer.getUserName() + " already exists.");
//                }
//                else {
//                    userDirectoryService.addUser(null, lecturer.getUserName(),
//                        lecturer.getFirstName(), lecturer.getSurname(), lecturer.getEmail(),
//                        lecturer.getPassword(), "maintain", null);
//                    log.info("Added user " + lecturer.getUserName());
//                }
//            }
//        }
//        catch (Exception e2) {
//            log.error("CourseMgmtPopulationJob - createLecturerSakaiUsers - Exception occured: ",
//                e2);
//        }
//    }
//
//    private void createStudentSakaiUsers(Set<Student> students) {
//        try {
//            for (Student student : students) {
//                String studentUserId = null;
//                try {
//                    studentUserId = userDirectoryService.getUserId(student.getUserName());
//                }
//                catch (Exception e) {
//                    studentUserId = "";
//                }
//                boolean studentExists = studentUserId != null && !studentUserId.equals("");
//                if (studentExists) {
//                    log.info("Student " + student.getUserName() + " already exists.");
//                }
//                else {
//                    userDirectoryService.addUser(null, student.getUserName(),
//                        student.getFirstName(), student.getSurname(), student.getEmail(),
//                        student.getPassword(), "student", null);
//                    log.info("Added user " + student.getUserName());
//                }
//            }
//        }
//        catch (Exception e2) {
//            log.error("CourseMgmtPopulationJob - createStudentSakaiUsers - Exception occured: ", e2);
//        }
//    }
}
