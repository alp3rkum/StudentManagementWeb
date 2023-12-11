package com.j01.StudentManagementWeb.DataAccessLayer;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.Entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class HibernateStudentDal implements IStudentDal {

	private EntityManager _entityManager;
	
	@Autowired
	public HibernateStudentDal(EntityManager entityManager) {
		//super();
		this._entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Student getById(int id) {
		Session session = _entityManager.unwrap(Session.class);
		Student student = session.get(Student.class, id);
		return student;
	}

	@Override
	@Transactional
	public List<Student> getAll() {
		Session session = _entityManager.unwrap(Session.class);
		List<Student> students = session.createQuery("from Student",Student.class).getResultList();
		return students;
	}

	@Override
	public void add(Student student) {
		Session session = _entityManager.unwrap(Session.class);
		session.save(student);
	}

	@Override
	public void update(Student student) {
		Session session = _entityManager.unwrap(Session.class);
		session.saveOrUpdate(student);
	}

	@Override
	public void delete(Student student) {
		Session session = _entityManager.unwrap(Session.class);
		Student studentToDelete = session.get(Student.class, student.get_id());
		session.delete(studentToDelete);
	}

	@Override
	public Student getByStudentId(int studentID) {
	    Session session = _entityManager.unwrap(Session.class);
	    Query query = session.createQuery("SELECT s FROM Student s WHERE s._studentID = :studentID");
	    query.setParameter("studentID", studentID);
	    return (Student) query.getSingleResult();
	}

}
