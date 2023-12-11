package com.j01.StudentManagementWeb.DataAccessLayer;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.Entities.Course;
import com.j01.StudentManagementWeb.Entities.Enrollment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

@Repository
public class HibernateEnrollmentDal implements IEnrollmentDal {

	private EntityManager _entityManager;
	
	@Autowired
	public HibernateEnrollmentDal(EntityManager _entityManager) {
		this._entityManager = _entityManager;
	}

	@Override
	@Transactional
	public List<Enrollment> getAll() {
		Session session = _entityManager.unwrap(Session.class);
		List<Enrollment> enrollments = session.createQuery("from Enrollment",Enrollment.class).getResultList();
		return enrollments;
	}

	@Override
	public Enrollment getById(int id) {
		Session session = _entityManager.unwrap(Session.class);
		Enrollment enrollment = session.get(Enrollment.class, id);
		return enrollment;
	}

	@Override
	public void add(Enrollment enrollment) {
		Session session = _entityManager.unwrap(Session.class);
		session.save(enrollment);
		
	}

	@Override
	public void update(Enrollment enrollment) {
		Session session = _entityManager.unwrap(Session.class);
		session.saveOrUpdate(enrollment);
		
	}

	@Override
	public void delete(Enrollment enrollment) {
		Session session = _entityManager.unwrap(Session.class);
		Enrollment enrollmentToDelete = session.get(Enrollment.class, enrollment.get_id());
		session.delete(enrollmentToDelete);
		
	}

	@Override
	public List<Course> getAllByStudentID(int studentID) {
		Session session = _entityManager.unwrap(Session.class);
		String SQLString = "SELECT new com.j01.StudentManagementWeb.Entities.Course(c._id, c._courseID, c._courseName, c._gradeYear, c._tuitionCost) FROM Enrollment e JOIN Course c ON e._Fk_Course_Id = c._courseID WHERE e._Fk_Student_Id = :studentID";
		Query query = session.createQuery(SQLString);
		query.setParameter("studentID", studentID);
		return query.getResultList();
	}

	@Override
	public List<Enrollment> getAllByStudentIDBackend(int studentID) {
		Session session = _entityManager.unwrap(Session.class);
		String HQL = "FROM Enrollment e WHERE e._Fk_Student_Id = :studentID";
		Query query = session.createQuery(HQL);
		query.setParameter("studentID", studentID);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteByStudentID(int studentID) {
		String queryString = "DELETE FROM Enrollment e WHERE e._Fk_Student_Id = :studentID";
	    Query query = _entityManager.createQuery(queryString);
	    query.setParameter("studentID", studentID);
	    query.executeUpdate();
	}

	
}
