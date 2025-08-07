package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUCourseLessonDao;
import za.ac.nwu.api.model.NWUGBLesson;

public class NWUCourseLessonDaoImpl extends HibernateDaoSupport implements NWUCourseLessonDao {

	@Override
	public NWUGBLesson updateLesson(NWUGBLesson lesson) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.saveOrUpdate(lesson);
		session.flush();
		return lesson;
	}

	@Override
	public List<NWUGBLesson> getLessons() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUGBLesson l");
		return (List<NWUGBLesson>) q.list();
	}

	@Override
	public NWUGBLesson getLessonById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUGBLesson l where l.sourceSystemId=:sourceSystemId");
		q.setParameter("sourceSystemId", id);
		return (NWUGBLesson) q.uniqueResult();
	}

	@Override
	public boolean deleteLesson(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWUGBLesson l where l.sourceSystemId=:sourceSystemId");
		q.setParameter("sourceSystemId", id);
		return q.executeUpdate() > 0;
	}

	@Override
	public List<NWUGBLesson> getLessonsByCourseIdAndDate(Long courseId, Date date) {

		List<NWUGBLesson> lessons = new ArrayList<>();

		HibernateCallback<List<NWUGBLesson>> hcb = session -> {

			Query q = null;
			if(date == null) {
				q = session.createQuery("SELECT l FROM NWUGBLesson l WHERE l.courseId = :courseId");
				q.setParameter("courseId", courseId);
			} else {
				q = session.createQuery("SELECT l FROM NWUGBLesson l WHERE l.courseId = :courseId AND l.auditDateTime > :date ");

				q.setParameter("courseId", courseId);
				q.setParameter("date", date.toInstant());
			}
			
			return q.list();
		};

		lessons = getHibernateTemplate().execute(hcb);

		return lessons;
	}
}
