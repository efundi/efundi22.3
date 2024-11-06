package za.ac.nwu.cm.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.user.api.UserDirectoryService;
import org.sakaiproject.user.api.UserNotDefinedException;

import lombok.extern.slf4j.Slf4j;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWUGBLesson;
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
     * @param course
     * @return courseSet
     */
    public static String getCourseSet(NWUCourse course) {
        return StringUtils.substring(course.getCourseCode(), 0, 4);
    }

    /**
      * Returns a CanonicalCourses id. (Examples: WISK111, AFNL111)
      */
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
            studentUserNames.add(Integer.toString(student.getNwuNumber()));
        }
        return studentUserNames;
    }

    public static LDAPRetrieval getLDAPRetrieval(
            ServerConfigurationService serverConfigurationService) {
        if (ldapRetrieval == null) {
            ldapRetrieval = new LDAPRetrieval(serverConfigurationService);
        }
        return ldapRetrieval;
    }

	/**
	 * Utility method for printing course info
	 * 
	 * @param courses
	 */
    public static void printCoursesInfo(List<NWUCourse> courses) {

		for (NWUCourse course : courses) {

			log.info("NWUCourse Info ============================================================ ");
			log.info("NWUCourse: " + course);
			
			NWULecturer lecturer = course.getLecturer();
			log.info("NWULecturer: " + lecturer);
			
			List<NWUStudentEnrollment> students = course.getStudents();
			if (students.isEmpty()) {
				log.info("No students found ");
			} else {
				for (NWUStudentEnrollment student : students) {
					log.info("NWUStudentEnrollment: " + student);
				}
			}
			
			List<NWUGBLesson> lessons = course.getLessons();
			if (lessons.isEmpty()) {
				log.info("No lessons found ");
			} else {
				for (NWUGBLesson lesson : lessons) {
					log.info("NWUGBLesson: " + lesson);
				}
			}
		}
	}
    
    public static void printLessonInfo(List<NWUGBLesson> lessons) {

		for (NWUGBLesson lesson : lessons) {
			log.info("NWULesson Info ============================================================ ");
			log.info("NWULesson: " + lesson);
						
		}
	}

	/**
	 * Returns the user Eid
	 * 
	 * @param userEid
	 * @return
	 */
	public static String getValidUserEid(UserDirectoryService userDirectoryService, String userEid) {
		try {
			return userDirectoryService.getUserId(userEid);
		} catch (UserNotDefinedException e) {
			log.error("User with eid " + userEid + " not found: " + e.toString());
		}
		return null;
	}
}