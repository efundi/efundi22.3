package za.ac.nwu.api.dao;

import java.util.Date;
import java.util.List;

import za.ac.nwu.api.model.NWUStudentEnrollment;

/**
 * 
 * DAO class for NWU Course enrollments
 * 
 * @author JC Gillman
 *
 */
public interface NWUCourseEnrollmentDao {

	public NWUStudentEnrollment updateEnrollment(NWUStudentEnrollment enrollment);

	public List<NWUStudentEnrollment> getEnrollments();

	public NWUStudentEnrollment getEnrollmentById(Long id);

	public boolean deleteEnrollment(Long id);

	public List<NWUStudentEnrollment> getEnrollmentsByCourseIdAndDate(Long courseId, Date date);

}
