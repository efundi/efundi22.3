package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUCourseEnrollmentDao;
import za.ac.nwu.api.model.NWUStudentEnrollment;

/**
 * NWU enrollment DAO implementation
 *
 * @author JC Gillman
 * 
 */
public class NWUCourseEnrollmentDaoImpl extends HibernateDaoSupport implements NWUCourseEnrollmentDao {

	@Override
	public NWUStudentEnrollment updateEnrollment(NWUStudentEnrollment enrollment) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		if (enrollment.getId() == null) {
//			enrollment.setCreatedDate(new Date());
//		}
		session.saveOrUpdate(enrollment);
		session.flush();
		return enrollment;
	}

	@Override
	public NWUStudentEnrollment getEnrollmentById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUStudentEnrollment e where e.id=:id");
		q.setParameter("id", id);
		return (NWUStudentEnrollment) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUStudentEnrollment> getEnrollments() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUStudentEnrollment e");
		return (List<NWUStudentEnrollment>) q.list();
	}

	@Override
	public boolean deleteEnrollment(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWUStudentEnrollment e where e.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUStudentEnrollment> getEnrollmentsByCourseIdAndDate(Long courseId, Date date) {

		List<NWUStudentEnrollment> enrollments = new ArrayList<>();

		HibernateCallback<List<NWUStudentEnrollment>> hcb = session -> {

			Query q = null;
			if(date == null) {
				q = session.createQuery("SELECT e FROM NWUStudentEnrollment e WHERE e.courseId = :courseId");
				q.setParameter("courseId", courseId);
			} else {
				q = session.createQuery("SELECT e FROM NWUStudentEnrollment e WHERE e.courseId = :courseId AND e.auditDateTime > :date ");

				q.setParameter("courseId", courseId);
				q.setParameter("date", date.toInstant());
			}
			
			return q.list();
		};

		enrollments = getHibernateTemplate().execute(hcb);

		return enrollments;
	}
}
