package za.ac.nwu.api.dao;

import java.util.Date;
import java.util.List;

import za.ac.nwu.api.model.NWUGBLesson;

/**
 * 
 * DAO class for NWU Course Lesson
 * 
 * @author JC Gillman
 *
 */
public interface NWUCourseLessonDao {

    public NWUGBLesson updateLesson(NWUGBLesson lesson);
    
    public List<NWUGBLesson> getLessons();
    
    public NWUGBLesson getLessonById(Long id);

    public boolean deleteLesson(Long id);

	public List<NWUGBLesson> getLessonsByCourseIdAndDate(Long courseId, Date date);

}
