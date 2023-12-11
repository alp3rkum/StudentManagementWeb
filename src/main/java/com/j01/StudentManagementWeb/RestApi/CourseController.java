package com.j01.StudentManagementWeb.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.j01.StudentManagementWeb.Business.ICourseService;
import com.j01.StudentManagementWeb.Entities.Course;

@RestController
@RequestMapping("/courseApi")
public class CourseController {
	private ICourseService _courseService;
	
	@Autowired
	public CourseController(ICourseService courseService) {
		//super();
		this._courseService = courseService;
	}
	
	@GetMapping("/courses")
	@Autowired
	public List<Course> get()
	{
		List<Course> courses = _courseService.getAll();
		return courses;
	}
	
	@GetMapping("/courses/{_id}")
	public Course getById(@PathVariable int _id) {
		Course course = _courseService.getById(_id);
		return course;
	}
	
	@GetMapping("/courses/{_courseID}")
	public Course getByCourseId(@PathVariable int _courseID) {
		Course course = _courseService.getByCourseID(_courseID);
		return course;
	}
	
	@PostMapping("/courses/add")
	public void add(@RequestBody Course course)
	{
		this._courseService.add(course);
	}
	
	@PostMapping("/courses/update")
	public void update(@RequestBody Course course)
	{
		this._courseService.update(course);
	}
	
	@PostMapping("/courses/delete")
	public void delete(@RequestBody Course course)
	{
		this._courseService.delete(course);
	}
}
