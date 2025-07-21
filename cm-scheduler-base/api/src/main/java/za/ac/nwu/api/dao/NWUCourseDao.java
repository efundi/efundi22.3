package za.ac.nwu.api.dao;

import java.util.Date;
import java.util.List;

import za.ac.nwu.api.model.NWUCourse;

/**
 * DAO class for NWU courses
 * 
 * @author JC Gillman
 *
 */
public interface NWUCourseDao {

	public NWUCourse updateCourse(NWUCourse course);

	public List<NWUCourse> getCourses();

	public NWUCourse getCourseById(Long id);

	public boolean deleteCourse(Long id);
	
	public List<NWUCourse> getAllCoursesWithNoSiteIdOrUpdated(Date date);	

	public List<NWUCourse> getAllCoursesWithSiteId();
	
	public NWUCourse findCourseForParams(String courseCode, String campusCode, String semCode);
}
