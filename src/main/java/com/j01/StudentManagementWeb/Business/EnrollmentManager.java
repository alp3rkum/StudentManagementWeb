package com.j01.StudentManagementWeb.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.DataAccessLayer.IEnrollmentDal;
import com.j01.StudentManagementWeb.Entities.Course;
import com.j01.StudentManagementWeb.Entities.Enrollment;

@Service
public class EnrollmentManager implements IEnrollmentService {

	private IEnrollmentDal _enrollmentDal;

	
	
	public EnrollmentManager(IEnrollmentDal enrollmentDal) {
		super();
		this._enrollmentDal = enrollmentDal;
	}

	@Override
	@Autowired
	@Transactional
	public List<Enrollment> getAll() {
		return this._enrollmentDal.getAll();
	}

	@Override
	public Enrollment getById(int id) {
		return this._enrollmentDal.getById(id);
	}

	@Override
	public void add(Enrollment enrollment) {
		this._enrollmentDal.add(enrollment);
		
	}

	@Override
	public void update(Enrollment enrollment) {
		this._enrollmentDal.update(enrollment);
		
	}

	@Override
	public void delete(Enrollment enrollment) {
		System.out.println("Delete");
		this._enrollmentDal.delete(enrollment);
		
	}

	@Override
	public List<Course> getAllByStudentID(int studentID) {
		return this._enrollmentDal.getAllByStudentID(studentID);
	}

	@Override
	public List<Enrollment> getAllByStudentIDBackend(int studentID) {
		return this._enrollmentDal.getAllByStudentIDBackend(studentID);
	}

	@Override
	public void deleteByStudentID(int studentID) {
		this._enrollmentDal.deleteByStudentID(studentID);
		
	}
}
