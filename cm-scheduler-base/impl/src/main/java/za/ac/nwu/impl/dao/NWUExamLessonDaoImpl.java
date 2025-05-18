package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUExamLessonDao;
import za.ac.nwu.api.model.NWUGBExamLesson;

public class NWUExamLessonDaoImpl extends HibernateDaoSupport implements NWUExamLessonDao {

	@Override
	public NWUGBExamLesson updateExamLesson(NWUGBExamLesson examLesson) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.saveOrUpdate(examLesson);
		session.flush();
		return examLesson;
	}


	@Override
	public List<NWUGBExamLesson> getExamLessons() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUGBExamLesson l");
		return (List<NWUGBExamLesson>) q.list();
	}

	@Override
	public NWUGBExamLesson getExamLessonById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUGBExamLesson l where l.id=:id");
		q.setParameter("id", id);
		return (NWUGBExamLesson) q.uniqueResult();
	}

	@Override
	public boolean deleteExamLesson(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWUGBExamLesson l where l.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	@Override
	public List<NWUGBExamLesson> getExamLessonsByCourseAndDate(String campus, String term, String courseCode,
			String sectionCode) {

		List<NWUGBExamLesson> examLessons = new ArrayList<>();

		HibernateCallback<List<NWUGBExamLesson>> hcb = session -> {

			Query q = session.createQuery("SELECT l FROM NWUGBExamLesson l WHERE l.campus = :campus AND l.term = :term AND l.courseCode = :courseCode	AND l.sectionCode = :sectionCode");
			q.setParameter("campus", campus);
			q.setParameter("term", term);
			q.setParameter("courseCode", courseCode);
			q.setParameter("sectionCode", sectionCode);
			
			return q.list();
		};

		examLessons = getHibernateTemplate().execute(hcb);

		return examLessons;
	}
}
