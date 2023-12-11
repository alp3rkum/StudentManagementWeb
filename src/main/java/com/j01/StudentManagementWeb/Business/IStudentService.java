package com.j01.StudentManagementWeb.Business;

import java.util.List;

import com.j01.StudentManagementWeb.Entities.Student;

public interface IStudentService {
	Student getById(int id);
	List<Student> getAll();
	Student getByStudentID(int studentID);
	void add(Student student);
	void update(Student student);
	void delete(Student student);
}
