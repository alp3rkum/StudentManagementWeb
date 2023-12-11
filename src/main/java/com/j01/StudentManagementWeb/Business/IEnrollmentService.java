package com.j01.StudentManagementWeb.Business;

import java.util.List;

import com.j01.StudentManagementWeb.Entities.Course;
import com.j01.StudentManagementWeb.Entities.Enrollment;

public interface IEnrollmentService {
	List<Enrollment> getAll();
	List<Course> getAllByStudentID(int studentID);
	List<Enrollment> getAllByStudentIDBackend(int studentID);
	Enrollment getById(int id);
	void add(Enrollment enrollment);
	void update(Enrollment enrollment);
	void delete(Enrollment enrollment);
	void deleteByStudentID(int studentID);
}
