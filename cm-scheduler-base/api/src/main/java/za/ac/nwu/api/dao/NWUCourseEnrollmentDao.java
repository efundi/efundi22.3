package za.ac.nwu.api.dao;

import java.util.List;

import za.ac.nwu.api.model.NWUEnrollment;

/**
 * 
 * DAO class for NWU Course enrollments
 * 
 * @author JC Gillman
 *
 */
public interface NWUCourseEnrollmentDao {

	public NWUEnrollment updateEnrollment(NWUEnrollment enrollment);

	public List<NWUEnrollment> getEnrollments();

	public List<NWUEnrollment> getEnrollmentsByAcadYear(int year);

	public List<NWUEnrollment> getEnrollmentsForCurrentAndNextAcadYear(int year);

	public NWUEnrollment getEnrollmentById(Long id);

	public boolean deleteEnrollment(Long id);

	public List<NWUEnrollment> getEnrollmentsByAcadYearOrderBySakaiSiteId(int year);

}
