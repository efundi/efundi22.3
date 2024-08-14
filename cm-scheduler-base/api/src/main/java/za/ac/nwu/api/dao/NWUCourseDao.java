package za.ac.nwu.api.dao;

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

	public List<NWUCourse> getCoursesByAcadYear(int year);
	
	public NWUCourse findCourseForParams(int acadYear, String courseCode, String campusCode, String semCode);
}
