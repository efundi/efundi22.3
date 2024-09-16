package za.ac.nwu.api.dao;

import java.util.Date;
import java.util.List;

import za.ac.nwu.api.model.NWULecturer;
/**
 * 
 * DAO class for NWU Course lecturers
 * 
 * @author JC Gillman
 *
 */
public interface NWUCourseLecturerDao {

    public NWULecturer updateLecturer(NWULecturer lecturer);
    
    public List<NWULecturer> getLecturers();
    
    public NWULecturer getLecturerById(Long id);

    public boolean deleteLecturer(Long id);

	public List<NWULecturer> getLecturersByCourseIdAndDate(Long courseId, Date date);

}
