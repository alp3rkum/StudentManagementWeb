package com.j01.StudentManagementWeb.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.DataAccessLayer.IStudentDal;
import com.j01.StudentManagementWeb.Entities.Student;

@Service
public class StudentManager implements IStudentService {

	private IStudentDal _studentDal;
	
	public StudentManager(IStudentDal studentDal) {
		//super();
		this._studentDal = studentDal;
	}

	@Override
	@Autowired
	@Transactional
	public List<Student> getAll() {
		//TODO business rules
		return this._studentDal.getAll();
	}
	
	@Override
	@Transactional
	public Student getById(int id) {
		//TODO business rules
		return this._studentDal.getById(id);
	}
	
	public Student getByStudentID(int studentID) {
		try
		{
			return this._studentDal.getByStudentId(studentID);
		}
		catch(Exception e)
		{
			Student nullStudent = new Student();
			nullStudent.set_firstName("Null");
			nullStudent.set_lastName("Null");
			return nullStudent;
		}
	}

	@Override
	@Transactional
	public void add(Student student) {
		boolean canBeAdded = (student.get_studentID() < 1) || (student.get_firstName().length() < 2) || (student.get_lastName().length() < 2) || (student.get_gradeYear() < 0) || (student.get_gradeYear() > 4) || (student.get_firstName() != "Null") || (student.get_lastName() != "Null");
		if(!canBeAdded)
		{
			this._studentDal.add(student);
		}
		else
		{
			throw new IllegalArgumentException("Invalid student data. Please provide valid values for student ID, first name, last name, and grade year.");
		}
	}

	@Override
	@Transactional
	public void update(Student student) {
		//TODO business rules
		this._studentDal.update(student);
	}

	@Override
	@Transactional
	public void delete(Student student) {
		//TODO business rules
		this._studentDal.delete(student);
	}

}
