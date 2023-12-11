package com.j01.StudentManagementWeb.DataAccessLayer;

import java.util.List;

import com.j01.StudentManagementWeb.Entities.Course;

public interface ICourseDal {
	List<Course> getAll();
	Course getById(int id);
	Course getByCourseId(int courseID);
	void add(Course course);
	void update(Course course);
	void delete(Course course);
}
