package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUCourseEnrollmentDao;
import za.ac.nwu.api.model.NWUEnrollment;

/**
 * NWU enrollment DAO implementation
 *
 * @author JC Gillman
 * 
 */
public class NWUCourseEnrollmentDaoImpl extends HibernateDaoSupport implements NWUCourseEnrollmentDao {

	@Override
	public NWUEnrollment updateEnrollment(NWUEnrollment enrollment) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		if (enrollment.getId() == null) {
//			enrollment.setCreatedDate(new Date());
//		}
		session.saveOrUpdate(enrollment);
		session.flush();
		return enrollment;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUEnrollment> getEnrollmentsByAcadYear(int year) {

		List<NWUEnrollment> enrollments = new ArrayList<>();

		HibernateCallback<List<NWUEnrollment>> hcb = session -> {
			Query q = session.getNamedQuery("FindEnrollmentsByAcadYear");
			q.setParameter("acadYear", year);
			return q.list();
		};

		enrollments = getHibernateTemplate().execute(hcb);

		return enrollments;
	}

	@Override
	public NWUEnrollment getEnrollmentById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUEnrollment e where e.id=:id");
		q.setParameter("id", id);
		return (NWUEnrollment) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUEnrollment> getEnrollments() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUEnrollment e");
		return (List<NWUEnrollment>) q.list();
	}

	@Override
	public boolean deleteEnrollment(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWUEnrollment e where e.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUEnrollment> getEnrollmentsByAcadYearOrderBySakaiSiteId(int year) {

		List<NWUEnrollment> enrollments = new ArrayList<>();

		HibernateCallback<List<NWUEnrollment>> hcb = session -> {
			Query q = session.getNamedQuery("FindEnrollmentsByAcadYearOrderBySakaiSiteId");
			q.setParameter("acadYear", year);
			return q.list();
		};

		enrollments = getHibernateTemplate().execute(hcb);

		return enrollments;
	}

	@Override
	public List<NWUEnrollment> getEnrollmentsForCurrentAndNextAcadYear(int year) {

		List<NWUEnrollment> enrollments = new ArrayList<>();

		HibernateCallback<List<NWUEnrollment>> hcb = session -> {
			Query q = session.getNamedQuery("FindEnrollmentsForCurrentAndNextAcadYear");
			q.setParameter("acadYear", year);
			q.setParameter("acadYearNext", year + 1);
			return q.list();
		};

		enrollments = getHibernateTemplate().execute(hcb);

		return enrollments;
	}
}
