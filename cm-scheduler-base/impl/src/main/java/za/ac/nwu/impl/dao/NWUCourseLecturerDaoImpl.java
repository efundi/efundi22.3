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
	public List<NWULecturer> getLecturersByAcadYearOrderBySakaiSiteId(int year) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {
			Query q = session.getNamedQuery("FindLecturersByAcadYearOrderBySakaiSiteId");
			q.setParameter("acadYear", year);
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWULecturer> getLecturersByAcadYear(int year) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {
			Query q = session.getNamedQuery("FindLecturersByAcadYear");
			q.setParameter("acadYear", year);
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}

	@Override
	public List<NWULecturer> getLecturersForCurrentAndNextAcadYear(int year) {

		List<NWULecturer> lecturers = new ArrayList<>();

		HibernateCallback<List<NWULecturer>> hcb = session -> {
			Query q = session.getNamedQuery("FindLecturersForCurrentAndNextAcadYear");
			q.setParameter("acadYear", year);
			q.setParameter("acadYearNext", year + 1);
			return q.list();
		};

		lecturers = getHibernateTemplate().execute(hcb);

		return lecturers;
	}
}
