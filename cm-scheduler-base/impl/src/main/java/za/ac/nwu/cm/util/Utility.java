package za.ac.nwu.cm.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.sakaiproject.component.api.ServerConfigurationService;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWULecturer;
import za.ac.nwu.api.model.NWUStudentEnrollment;

@Slf4j
public class Utility {

    private static LDAPRetrieval ldapRetrieval;

    /**
     * A simple method that generates the hashCode by taking the sum of the parameter hashCodes. If
     * a parameter value is null a default number(old nr. 7) is used.
     */
    public static int hashCode(Object... objects) {
        int hash = 0;
        for (Object obj : objects) {
            hash += obj == null ? 7 : obj.hashCode();
        }
        return hash;
    }

    /**
     * A method that tests for String equality.
     */
    public static boolean equals(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    /**
     * Returns a unique set of CourseSet Ids. (Examples: WISK, AFNL)
     * @param cmStructures
     * @return
     */
//    public static Set<String> getCourseSetIds(final Set<Module> modules) {
//        Set<String> courseSetIds = new HashSet<String>();
//        for (Module module : modules) {
//            courseSetIds.add(module.getCourseCode());
//        }
//        return courseSetIds;
//    }
    public static String getCourseSet(NWUCourse course) { //ECCR411
        return StringUtils.substring(course.getCourseCode(), 0, 4);
    }


    /**
      * Returns a CanonicalCourses id. (Examples: WISK111, AFNL111)
      */
//    public static Set<String> getCanonicalCourseIds(final Set<Module> modules) {
//        Set<String> canonicalCourseIds = new HashSet<String>();
//        for (Module module : modules) {
//            canonicalCourseIds.add(module.getCanonicalCourseReference());
//        }
//        return canonicalCourseIds;
//    }
    public static String getCanonicalCourseReference(NWUCourse course) {
        return course.getCourseCode();
    } 

    /**
     * Course Management Helper method - CourseOffering eid example: Course –Campus-Term-(section code)
     */
    public static String getCourseOfferingReference(NWUCourse course) {
        return getCanonicalCourseReference(course) + "-" + course.getCampus() + "-" + course.getTerm()  + "-(" +  course.getSectionCode()  + ")";
    }

    /**
     * Course Management Helper method - EnrollmentSet eid example: WISK 111 P 2011 ES
     */
    public static String getEnrollmentSetReference(NWUCourse course) {
        return getCourseOfferingReference(course) + " ES";
    }
    
    /**
     * Returns a unique set of Lecturer user names. (Examples: david, john123)
     */
//    public static Set<String> getLecturerUserNames(final Module module) {
//        Set<String> lecturerUserNames = new HashSet<String>();
//        for (Lecturer lecturer : module.getLinkedLecturers()) {
//            lecturerUserNames.add(lecturer.getUserName());
//        }
//        return lecturerUserNames;
//    }
    public static Set<String> getLecturerUserNames(final NWUCourse course) {
        Set<String> lecturerUserNames = new HashSet<String>();
//        lecturerUserNames.add("" + course.getInstructorNumber());
        return lecturerUserNames;
    }
    
    /**
     * Returns a unique set of Student user names. (Examples: stud123, stud456)
     */
    public static Set<String> getStudentUserNames(final NWUCourse course) {
        Set<String> studentUserNames = new HashSet<String>();
        for (NWUStudentEnrollment student : course.getStudents()) {
            studentUserNames.add("" + student.getNwuNumber());
        }
        return studentUserNames;
    }
//
//    /**
//     * Returns a unique set of Lecturers linked to a CourseSet.
//     */
//    public static Set<String> getLecturersLinkedToCourseSet(final String courseSetId,
//            final Set<Module> modules) {
//        Set<String> lecturerUserNames = new HashSet<String>();
//        for (Module module : modules) {
//            if (Utility.equals(courseSetId, module.getCourseCode())) {
//                for (Lecturer lecturer : module.getLinkedLecturers()) {
//                    lecturerUserNames.add(lecturer.getUserName());
//                }
//            }
//        }
//        return lecturerUserNames;
//    }

    public static LDAPRetrieval getLDAPRetrieval(
            ServerConfigurationService serverConfigurationService) {
        if (ldapRetrieval == null) {
            ldapRetrieval = new LDAPRetrieval(serverConfigurationService);
        }
        return ldapRetrieval;
    }
}