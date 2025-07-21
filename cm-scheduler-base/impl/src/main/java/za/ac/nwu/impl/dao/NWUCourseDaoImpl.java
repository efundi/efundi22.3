package za.ac.nwu.impl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import za.ac.nwu.api.dao.NWUCourseDao;
import za.ac.nwu.api.model.NWUCourse;

/**
 * NWU course DAO implementation
 *
 * @author JC Gillman
 * 
 */
public class NWUCourseDaoImpl extends HibernateDaoSupport implements NWUCourseDao {

	@Override
	public NWUCourse updateCourse(NWUCourse course) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		if (course.getId() == null) {
//			course.setCreateDate(new Date());
//		} else {
//			course.setModifiedDate(new Date());
//		}
		session.saveOrUpdate(course);
		session.flush();
		return course;
	}

	@Override
	public NWUCourse getCourseById(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUCourse c where c.id=:id");
		q.setParameter("id", id);
		return (NWUCourse) q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NWUCourse> getCourses() {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("from NWUCourse c");
		return (List<NWUCourse>) q.list();
	}

	@Override
	public boolean deleteCourse(Long id) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery("delete from NWUCourse c where c.id=:id");
		q.setParameter("id", id);
		return q.executeUpdate() > 0;
	}

	

	@Override
	public NWUCourse findCourseForParams(String courseCode, String campusCode, String semCode) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query q = session.createQuery(
				"from NWUCourse c where c.courseCode = :courseCode and c.campusCode = :campusCode and c.semCode = :semCode");
		q.setParameter("courseCode", courseCode);
		q.setParameter("campusCode", campusCode);
		q.setParameter("semCode", semCode);
		return (NWUCourse) q.uniqueResult();
	}

	@Override
	public List<NWUCourse> getAllCoursesWithNoSiteIdOrUpdated(Date date) {

		List<NWUCourse> courses = new ArrayList<>();

		HibernateCallback<List<NWUCourse>> hcb = session -> {
			
			Query q = null;
			if(date == null) {
				q = session.createQuery("SELECT c FROM NWUCourse c WHERE c.efundiSiteId is null OR c.efundiSiteId = '' ");
			} else {
				q = session.createQuery("SELECT c FROM NWUCourse c WHERE c.efundiSiteId is null OR c.efundiSiteId = '' OR c.auditDateTime > :date ");
				q.setParameter("date", date.toInstant());
			}
			return q.list();
		};

		courses = getHibernateTemplate().execute(hcb);

		return courses;
	}

	@Override
	public List<NWUCourse> getAllCoursesWithSiteId() {

		List<NWUCourse> courses = new ArrayList<>();

		HibernateCallback<List<NWUCourse>> hcb = session -> {
			Query q = session.getNamedQuery("FindAllCoursesWithSiteId");
			return q.list();
		};

		courses = getHibernateTemplate().execute(hcb);

		return courses;
	}
}
