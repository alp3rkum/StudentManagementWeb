package com.j01.StudentManagementWeb.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.j01.StudentManagementWeb.Business.IEnrollmentService;
import com.j01.StudentManagementWeb.Entities.Course;
import com.j01.StudentManagementWeb.Entities.Enrollment;

@RestController
@RequestMapping("/enrollmentApi")
public class EnrollmentController {

	private IEnrollmentService _enrollmentService;

	public EnrollmentController(IEnrollmentService enrollmentService) {
		//super();
		this._enrollmentService = enrollmentService;
	}
	
	@GetMapping("/enrollments")
	@Autowired
	public List<Enrollment> get()
	{
		List<Enrollment> enrollments = _enrollmentService.getAll();
		return enrollments;
	}
	
	@GetMapping("/enrollments/{_id}")
	public Enrollment getById(@PathVariable int _id) {
		Enrollment enrollment = _enrollmentService.getById(_id);
		return enrollment;
	}
	
	@PostMapping("/enrollments/add")
	public void add(@RequestBody Enrollment enrollment)
	{
		this._enrollmentService.add(enrollment);
	}
	
	@PostMapping("/enrollments/update")
	public void update(@RequestBody Enrollment enrollment)
	{
		this._enrollmentService.update(enrollment);
	}
	
	@PostMapping("/enrollments/delete")
	public void delete(@RequestBody Enrollment enrollment)
	{
		this._enrollmentService.delete(enrollment);
	}
	
	@PostMapping("/enrollments/byStudentId/{_studentID}")
	public List<Course> getByStudentID(@PathVariable int _studentID) {
		List<Course> enrollments = _enrollmentService.getAllByStudentID(_studentID);
		return enrollments;
	}
	
	@PostMapping("/enrollments/byStudentId/backend/{_studentID}")
	public List<Enrollment> getByStudentIDBackend(@PathVariable int _studentID) {
		List<Enrollment> enrollments = _enrollmentService.getAllByStudentIDBackend(_studentID);
		return enrollments;
	}
	
	@PostMapping("/enrollments/delete/{_studentID}")
	public void deleteByStudentID(@PathVariable int _studentID)
	{
		this._enrollmentService.deleteByStudentID(_studentID);
	}
}
