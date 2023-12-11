package com.j01.StudentManagementWeb.RestApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.j01.StudentManagementWeb.Business.IStudentService;
import com.j01.StudentManagementWeb.Entities.Student;

@RestController
@RequestMapping("/api")
public class StudentController {
	private IStudentService _studentService;

	@Autowired
	public StudentController(IStudentService studentService) {
		//super();
		this._studentService = studentService;
	}
	
	@GetMapping("/students")
	@Autowired
	public List<Student> get()
	{
		List<Student> students = _studentService.getAll();
		return students;
	}
	@GetMapping("/students/{_id}")
	public Student getById(@PathVariable int _id) {
		Student student = _studentService.getById(_id);
		return student;
	}
	@GetMapping("/students/{_studentID}")
	public Student getByStudentID(@PathVariable int _studentID) {
		Student student = _studentService.getByStudentID(_studentID);
		return student;
	}
	
	@PostMapping("/students/add")
	public void add(@RequestBody Student student)
	{
		this._studentService.add(student);
	}
	
	@PostMapping("/students/update")
	public void update(@RequestBody Student student)
	{
		this._studentService.update(student);
	}
	
	@PostMapping("/students/delete")
	public void delete(@RequestBody Student student)
	{
		this._studentService.delete(student);
	}
}
