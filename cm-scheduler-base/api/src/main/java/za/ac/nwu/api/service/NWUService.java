package za.ac.nwu.api.service;

import java.util.Date;

import org.quartz.JobExecutionException;

/**
 * 
 * NWU Service interface
 * 
 * @author JC Gillman
 *
 */
public interface NWUService {

	public void updateNWUCourseManagement() throws JobExecutionException;

	public void updateNWUeFundiCourseSites() throws JobExecutionException;

	public void updateNWUCourseEnrollments(Date previousFireTime) throws JobExecutionException;

	public void updateNWUCourseLecturers(Date previousFireTime) throws JobExecutionException;

	public void updateNWULessonStudentGrades(Date previousFireTime) throws JobExecutionException;;

	public void updateNWUCourseLessonPlans(Date previousFireTime) throws JobExecutionException;

}
