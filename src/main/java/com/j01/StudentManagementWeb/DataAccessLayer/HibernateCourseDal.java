package com.j01.StudentManagementWeb.DataAccessLayer;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.Entities.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class HibernateCourseDal implements ICourseDal {

	private EntityManager _entityManager;
	
	@Autowired
	public HibernateCourseDal(EntityManager entityManager) {
		//super();
		this._entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Course> getAll() {
		Session session = _entityManager.unwrap(Session.class);
		List<Course> courses = session.createQuery("from Course",Course.class).getResultList();
		return courses;
	}

	@Override
	public Course getById(int id) {
		Session session = _entityManager.unwrap(Session.class);
		Course course = session.get(Course.class, id);
		return course;
	}

	@Override
	public Course getByCourseId(int courseID) {
		Session session = _entityManager.unwrap(Session.class);
	    Query query = session.createQuery("SELECT c FROM Course c WHERE c._courseID = :courseID");
	    query.setParameter("courseID", courseID);
	    return (Course) query.getSingleResult();
	}

	@Override
	public void add(Course course) {
		Session session = _entityManager.unwrap(Session.class);
		session.save(course);
	}

	@Override
	public void update(Course course) {
		Session session = _entityManager.unwrap(Session.class);
		session.saveOrUpdate(course);
		
	}

	@Override
	public void delete(Course course) {
		Session session = _entityManager.unwrap(Session.class);
		Course courseToDelete = session.get(Course.class, course.get_id());
		session.delete(courseToDelete);
	}
}
