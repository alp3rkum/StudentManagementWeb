package com.j01.StudentManagementWeb.Business;

import java.util.List;

import com.j01.StudentManagementWeb.Entities.Course;

public interface ICourseService {
	List<Course> getAll();
	Course getById(int id);
	Course getByCourseID(int courseID);
	void add(Course course);
	void update(Course course);
	void delete(Course course);
}
