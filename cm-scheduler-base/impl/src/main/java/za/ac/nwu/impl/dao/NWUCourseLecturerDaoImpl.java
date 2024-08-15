package za.ac.nwu.impl.dao;

import java.util.ArrayList;
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
	public List<NWULecturer> getLecturersByYearOrderBySakaiSiteId(int year) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {
			Query q = session.getNamedQuery("FindLecturersByYearOrderBySakaiSiteId");
			q.setParameter("year", year);
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWULecturer> getLecturersByYear(int year) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {
			Query q = session.getNamedQuery("FindLecturersByYear");
			q.setParameter("year", year);
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}
}
