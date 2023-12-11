package com.j01.StudentManagementWeb.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.j01.StudentManagementWeb.DataAccessLayer.ICourseDal;
import com.j01.StudentManagementWeb.Entities.Course;

@Service
public class CourseManager implements ICourseService {

	private ICourseDal _courseDal;
	
	public CourseManager(ICourseDal courseDal) {
		super();
		this._courseDal = courseDal;
	}

	@Override
	@Autowired
	@Transactional
	public List<Course> getAll() {
		return this._courseDal.getAll();
	}

	@Override
	@Transactional
	public Course getByCourseID(int courseID) {
		try
		{
			return this._courseDal.getByCourseId(courseID);
		}
		catch(Exception e)
		{
			Course nullCourse = new Course();
			nullCourse.set_courseName("Null");
			return nullCourse;
		}
	}

	@Override
	@Transactional
	public void add(Course course) {
		// TODO Business Rules
		boolean cantBeAdded = (course.get_courseID() < 1) || (course.get_courseName().length() < 1) || (course.get_courseName() == "Null") || (course.get_gradeYear() < 0) || (course.get_tuitionCost() < 100);
		if(!(cantBeAdded))
			this._courseDal.add(course);
		else
			throw new IllegalArgumentException("Invalid course data. Please provide valid values for course ID, course name, grade year, and tuition cost.");
	}

	@Override
	@Transactional
	public void update(Course course) {
		// TODO Auto-generated method stub
		this._courseDal.update(course);
	}

	@Override
	@Transactional
	public void delete(Course course) {
		// TODO Auto-generated method stub
		this._courseDal.delete(course);
	}

	@Override
	public Course getById(int id) {
		return this._courseDal.getById(id);
	}

}
