package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWULessonGradeDao;
import za.ac.nwu.api.model.NWUCourse;
import za.ac.nwu.api.model.NWULessonGrade;


public class NWULessonGradeDaoImpl extends HibernateDaoSupport implements NWULessonGradeDao {

	@Override
	public NWULessonGrade updateLessonGrade(NWULessonGrade lesson_grade) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.saveOrUpdate(lesson_grade);
		session.flush();
		return lesson_grade;
	}

	@Override
	public List<NWULessonGrade> getLessonGrades() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWULessonGrade c");
		return (List<NWULessonGrade>) q.list();
	}

	@Override
	public NWULessonGrade getLessonGradeById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWULessonGrade c where c.id=:id");
		q.setParameter("id", id);
		return (NWULessonGrade) q.uniqueResult();
	}

	@Override
	public boolean deleteLessonGrade(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NwuLessonGrade c where c.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	@Override
	public List<NWULessonGrade> getAllGradesByLessonId() {
		List<NWULessonGrade> grades = new ArrayList<>();

		HibernateCallback<List<NWULessonGrade>> hcb = session -> {
			Query q = session.getNamedQuery("FindAllGradesByLessonId");
			return q.list();
		};

		grades = getHibernateTemplate().execute(hcb);

		return grades;
	}

	@Override

	public List<NWULessonGrade> getAllGradesbyNwuNumber(int nuwNumber) {

		List<NWULessonGrade> grades = new ArrayList<>();

		HibernateCallback<List<NWULessonGrade>> hcb = session -> {
			Query q = session.getNamedQuery("FindAllGradesbyNwuNumber");
			return q.list();
		};

		grades = getHibernateTemplate().execute(hcb);

		return grades;
	}
}