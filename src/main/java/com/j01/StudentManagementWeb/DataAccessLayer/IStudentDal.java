package com.j01.StudentManagementWeb.DataAccessLayer;

import java.util.List;

import com.j01.StudentManagementWeb.Entities.Student;

public interface IStudentDal {
	Student getById(int id);
	Student getByStudentId(int studentID);
	List<Student> getAll();
	void add(Student student);
	void update(Student student);
	void delete(Student student);
}
