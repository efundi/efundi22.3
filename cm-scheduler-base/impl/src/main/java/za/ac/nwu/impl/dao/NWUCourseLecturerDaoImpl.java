package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUCourseLecturerDao;
import za.ac.nwu.api.model.NWULecturer;

/**
 * NWU lecturer DAO implementation
 *
 * @author JC Gillman
 * 
 */
public class NWUCourseLecturerDaoImpl extends HibernateDaoSupport implements NWUCourseLecturerDao {

	@Override
	public NWULecturer updateLecturer(NWULecturer lecturer) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.saveOrUpdate(lecturer);
		session.flush();
		return lecturer;
	}

	@Override
	public NWULecturer getLecturerById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWULecturer l where l.id=:id");
		q.setParameter("id", id);
		return (NWULecturer) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWULecturer> getLecturers() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWULecturer l");
		return (List<NWULecturer>) q.list();
	}

	@Override
	public boolean deleteLecturer(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWULecturer l where l.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWULecturer> getLecturersByCourseIdAndDate(Long courseId, Date date) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {

			Query q = null;
			if(date == null) {
				q = session.createQuery("SELECT l FROM NWULecturer l WHERE l.courseId = :courseId");
				q.setParameter("courseId", courseId);
			} else {
				q = session.createQuery("SELECT l FROM NWULecturer l WHERE l.courseId = :courseId AND l.auditDateTime > :date ");

				q.setParameter("courseId", courseId);
				q.setParameter("date", date.toInstant());
			}
			
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}
}
