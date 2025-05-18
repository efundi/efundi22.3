package za.ac.nwu.api.dao;

import java.util.Date;
import java.util.List;

import za.ac.nwu.api.model.NWUGBExamLesson;

/**
 * 
 * DAO class for NWU Exam Lesson
 * 
 * @author JC Gillman
 *
 */
public interface NWUExamLessonDao {

    public NWUGBExamLesson updateExamLesson(NWUGBExamLesson examLesson);
    
    public List<NWUGBExamLesson> getExamLessons();
    
    public NWUGBExamLesson getExamLessonById(Long id);

    public boolean deleteExamLesson(Long id);

	public List<NWUGBExamLesson> getExamLessonsByCourseAndDate(String campus, String term, String courseCode, String sectionCode);

}
