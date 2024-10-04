package za.ac.nwu.api.dao;

import java.util.List;

import za.ac.nwu.api.model.NWULessonGrade;

/**
 * DAO class for NWU courses
 * 
 * @author BMcL
 *
 */
public interface NWULessonGradeDao {

	public NWULessonGrade updateLessonGrade(NWULessonGrade lesson_grade);

	public List<NWULessonGrade> getLessonGrades();

	public NWULessonGrade getLessonGradeById(Long id);

	public boolean deleteLessonGrade(Long id);
	
	public List<NWULessonGrade> getAllGradesByLessonId();	
	
	public List<NWULessonGrade> FindAllGradesbyNwuNumber(int nuwNumber);

}
