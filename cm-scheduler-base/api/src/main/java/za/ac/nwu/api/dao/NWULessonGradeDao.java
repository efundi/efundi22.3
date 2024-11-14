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

	public NWULessonGrade updateLessonGrade(NWULessonGrade lessonGrade);

	public List<NWULessonGrade> getLessonGrades();

	public NWULessonGrade getLessonGradeById(Long id);

	public NWULessonGrade getLessonGradeByLessonIdAndNwuNumber(Long lessonId, Integer nwuNumber);

	public boolean deleteLessonGrade(Long id);
	
	public List<NWULessonGrade> getAllGradesByLessonId(Long lessonId);
	
	public List<NWULessonGrade> getAllGradesbyNwuNumber(Integer nwuNumber);

}
