package com.j01.StudentManagementWeb.Frontend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.j01.StudentManagementWeb.Entities.Course;
import com.j01.StudentManagementWeb.Entities.Enrollment;
import com.j01.StudentManagementWeb.Entities.Student;
import com.j01.StudentManagementWeb.RestApi.CourseController;
import com.j01.StudentManagementWeb.RestApi.EnrollmentController;
import com.j01.StudentManagementWeb.RestApi.StudentController;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class FrontendController {

	private StudentController studentController;
	private CourseController courseController;
	private EnrollmentController enrollmentController;
	
	public FrontendController(StudentController studentController, CourseController courseController, EnrollmentController enrollmentController)
	{
		this.studentController = studentController;
		this.courseController = courseController;
		this.enrollmentController = enrollmentController;
	}
	
	private ModelAndView _mav = new ModelAndView();
	@RequestMapping("/")
	 public ModelAndView index() {
		_mav.setViewName("index.html");
		_mav.addObject("undertitle","Main Menu");
		return _mav;
	}
	
	@RequestMapping("/student")
	public ModelAndView studentMenu() {
		_mav.setViewName("student.html");
		_mav.addObject("undertitle","Student Operation Menu");
		return _mav;
	}

	@RequestMapping("/studentList")
	public ModelAndView studentList() {
		_mav.setViewName("studentList.html");
		List<Student> students = this.studentController.get();
		_mav.addObject("students",students);
		_mav.addObject("undertitle","Student List");
		return _mav;
	}
	
	@GetMapping("/studentInfo")
	public ModelAndView studentInfo(@RequestParam int studentID) {
		_mav.setViewName("studentInfo.html");
		System.out.println(studentID);
		Student student = this.studentController.getByStudentID(studentID);
		_mav.addObject("student",student);
		_mav.addObject("undertitle","Student Information");
		return _mav;
	}
	
	@GetMapping("/studentInfo/enrollments")
	public ModelAndView studentEnrollmentInfo(@RequestParam int _studentID) {
		_mav.setViewName("studentEnrollment/listEnrollments.html");
		Student student = this.studentController.getByStudentID(_studentID);
		List<Course> enrollments = this.enrollmentController.getByStudentID(_studentID);
		_mav.addObject("student",student);
		_mav.addObject("enrollments",enrollments);
		_mav.addObject("undertitle","Student Enrollment Information");
		return _mav;
	}
	
	@GetMapping("/addStudent")
	public ModelAndView addStudentMenu() {
		Student student = new Student();
	    student.set_id(0);
	    student.set_studentID(0);
	    student.set_firstName("");
	    student.set_lastName("");
	    student.set_gradeYear(0);
	    student.set_tuitionBalance(0);

	    _mav.setViewName("addStudent.html");
	    _mav.addObject("student", student);
	    _mav.addObject("undertitle","Add New Student");
		return _mav;
	}

	@PostMapping(value="/addStudent",headers="Content-type=application/json")
	public String addStudent(@RequestBody String source) {
		Student student = new Student();
		System.out.println(source);
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			System.out.println(par);
			String[] par_pars = par.split("\\=");
			System.out.println(par_pars[1]);
			switch(par_pars[0])
			{
				case "_studentID":
					student.set_studentID(Integer.parseInt(par_pars[1])); break;
				case "_firstName":
					String[] studentNameSplit = par_pars[1].split("\\+");
					String studentName = "";
					if(studentNameSplit.length == 1)
						studentName = studentNameSplit[0];
					else
					{
						for(String split : studentNameSplit)
						{
							studentName += split + " ";
						}
						studentName = studentName.substring(0,studentName.length()-1);
					}
					student.set_firstName(studentName); break;
				case "_lastName":
					student.set_lastName(par_pars[1]); break;
				case "_gradeYear":
					student.set_gradeYear(Integer.parseInt(par_pars[1])); break;
				case "_tuitionBalance":
					student.set_tuitionBalance(Integer.parseInt(par_pars[1])); break;
			}
		}
		System.out.println(student.get_studentID());
		this.studentController.add(student);
		return "redirect:/studentList";
	}
	
	@RequestMapping("/updateStudent")
	public ModelAndView updateStudentMenu() {
		_mav.setViewName("updateStudentMenu.html");
		_mav.addObject("undertitle","Student Update Menu");
		return _mav;
	}

	@GetMapping("/updateStudent/gradeup")
	public ModelAndView updateStudentGradeUp(@RequestParam int studentID) {
		_mav.setViewName("studentUpdate/studentGradeUp.html");
		Student student = this.studentController.getByStudentID(studentID);
		if(student.get_firstName() != "Null" && student.get_lastName() != "Null")
		{
			_mav.addObject("student", student);
			_mav.addObject("undertitle","Grade Up");
			return _mav;
		}
		else
		{
			_mav.setViewName("redirect:/student");
			return _mav;
		}
	}
	
	@PostMapping(value="/updateStudent/gradeup",headers="Content-type=application/json")
	public String StudentGradeUpEvent(@RequestBody String source) {
		System.out.println(source);
		String[] par = source.split("\\=");
		int studentID = Integer.parseInt(par[1]);
		Student student = this.studentController.getByStudentID(studentID);
		student.set_gradeYear(student.get_gradeYear() + 1);
		this.studentController.update(student);
		return "redirect:/studentList";
	}
	
	@GetMapping("/updateStudent/tuition")
	public ModelAndView updateStudentTuition(@RequestParam int studentID) {
		_mav.setViewName("studentUpdate/studentUpdateTuition.html");
		Student student = this.studentController.getByStudentID(studentID);
		if(student.get_firstName() != "Null" && student.get_lastName() != "Null")
		{
			_mav.addObject("student", student);
			_mav.addObject("undertitle","Update Student Tuition");
			return _mav;
		}
		else
		{
			_mav.setViewName("redirect:/student");
			return _mav;
		}
	}
	
	@PostMapping(value="/updateStudent/tuition",headers="Content-type=application/json")
	public String updateStudentTuitionEvent(@RequestBody String source) {
		System.out.println(source);
		int studentID = 0, tuitionUpdate = 0;
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			String[] par_pars = par.split("\\=");
			switch(par_pars[0])
			{
				case "_tuitionUpdate":
					tuitionUpdate = Integer.parseInt(par_pars[1]); break;
				case "_studentID":
					studentID = Integer.parseInt(par_pars[1]); break;
			}
		}
		Student student = this.studentController.getByStudentID(studentID);
		int currentTuition = student.get_tuitionBalance();
		int newTuition = currentTuition + tuitionUpdate;
		student.set_tuitionBalance(newTuition);
		this.studentController.update(student);
		return "redirect:/studentList";
	}
	
	@RequestMapping("/deleteStudent")
	public ModelAndView deleteStudentMenu() {
		_mav.setViewName("deleteStudent.html");
		_mav.addObject("undertitle","Delete Student");
		return _mav;
	}
	
	@GetMapping("/deleteStudent/confirm")
	public ModelAndView deleteStudentConfirm(@RequestParam int studentID) {
		_mav.setViewName("deleteStudentEvent.html");
		Student student = this.studentController.getByStudentID(studentID);
		if(student.get_firstName() != "Null" && student.get_lastName() != "Null")
		{
			_mav.addObject("student", student);
			_mav.addObject("undertitle","Confirm Deletion");
			return _mav;
		}
		else
		{
			_mav.setViewName("redirect:/student");
			return _mav;
		}
	}
	
	@PostMapping(value="/deleteStudent/confirm",headers="Content-type=application/json")
	public String deleteStudentEvent(@RequestBody String source)
	{
		String[] par = source.split("\\=");
		int studentID = Integer.parseInt(par[1]);
		Student student = this.studentController.getByStudentID(studentID);
		this.studentController.delete(student);
		return "redirect:/studentList";
	}
	
	@RequestMapping("/course")
	public ModelAndView courseMenu() {
		_mav.setViewName("course.html");
		_mav.addObject("undertitle","Course Operation Menu");
		return _mav;
	}
	
	@RequestMapping("/courseList")
	public ModelAndView courseList() {
		_mav.setViewName("courseList.html");
		List<Course> courses = this.courseController.get();
		_mav.addObject("courses",courses);
		_mav.addObject("undertitle","Course List");
		return _mav;
	}
	
	@GetMapping("/addCourse")
	public ModelAndView addCourseMenu() {
		Course course = new Course();
	    course.set_id(0);
	    course.set_courseID(0);
	    course.set_courseName("");
	    course.set_gradeYear(0);
	    course.set_tuitionCost(0);

	    _mav.setViewName("addCourse.html");
	    _mav.addObject("course", course);
	    _mav.addObject("undertitle","Add New Course");
		return _mav;
	}
	
	@PostMapping(value="/addCourse",headers="Content-type=application/json")
	public String addCourse(@RequestBody String source) {
		Course course = new Course();
		System.out.println(source);
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			String[] par_pars = par.split("\\=");
			System.out.println(par_pars[1]);
			switch(par_pars[0])
			{
				case "_courseID":
					course.set_courseID(Integer.parseInt(par_pars[1])); break;
				case "_courseName":
					String[] courseNameSplit = par_pars[1].split("\\+");
					String courseName = "";
					if(courseNameSplit.length == 1)
						courseName = courseNameSplit[0];
					else
					{
						for (String split : courseNameSplit)
						{
							courseName += split + " ";
						}
						courseName = courseName.substring(0,courseName.length()-1);
					}
					
					course.set_courseName(courseName); break;
				case "_gradeYear":
					course.set_gradeYear(Integer.parseInt(par_pars[1])); break;
				case "_tuitionCost":
					course.set_tuitionCost(Integer.parseInt(par_pars[1])); break;
			}
		}
		this.courseController.add(course);
		return "redirect:/courseList";
	}
	
	@RequestMapping("/updateCourse")
	public ModelAndView updateCourseMenu() {
		_mav.setViewName("updateCourseMenu.html");
		_mav.addObject("undertitle","Course Update Menu");
		return _mav;
	}
	
	@GetMapping("/updateCourse/grade")
	public ModelAndView updateCourseGrade(@RequestParam int courseID) {
		_mav.setViewName("courseUpdate/courseGrade.html");
		Course course = this.courseController.getByCourseId(courseID);
		_mav.addObject("course", course);
		_mav.addObject("undertitle","Change Course Grade");
		return _mav;
	}
	
	@PostMapping(value="/updateCourse/grade",headers="Content-type=application/json")
	public String CourseUpdateGradeEvent(@RequestBody String source) {
		System.out.println(source);
		int courseID = 0, courseGrade = 0;
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			String[] par_pars = par.split("\\=");
			switch(par_pars[0])
			{
				case "_gradeYear":
					courseGrade = Integer.parseInt(par_pars[1]); break;
				case "_courseID":
					courseID = Integer.parseInt(par_pars[1]); break;
			}
		}
		Course course = this.courseController.getByCourseId(courseID);
		course.set_gradeYear(courseGrade);
		this.courseController.update(course);
		return "redirect:/courseList";
	}
	
	@GetMapping("/updateCourse/tuition")
	public ModelAndView updateCourseTuition(@RequestParam int courseID) {
		_mav.setViewName("courseUpdate/courseTuition.html");
		Course course = this.courseController.getByCourseId(courseID);
		_mav.addObject("course", course);
		_mav.addObject("undertitle","Update Course Tuition Cost");
		return _mav;
	}
	
	@PostMapping(value="/updateCourse/tuition",headers="Content-type=application/json")
	public String updateCourseTuitionEvent(@RequestBody String source) {
		System.out.println(source);
		int courseID = 0, newTuition = 0;
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			String[] par_pars = par.split("\\=");
			switch(par_pars[0])
			{
				case "_newTuition":
					newTuition = Integer.parseInt(par_pars[1]); break;
				case "_courseID":
					courseID = Integer.parseInt(par_pars[1]); break;
			}
		}
		Course course = this.courseController.getByCourseId(courseID);
		course.set_tuitionCost(newTuition);
		this.courseController.update(course);
		return "redirect:/courseList";
	}
	
	@RequestMapping("/deleteCourse")
	public ModelAndView deleteCourseMenu() {
		_mav.setViewName("deleteCourse.html");
		_mav.addObject("undertitle","Delete Course");
		return _mav;
	}
	
	@GetMapping("/deleteCourse/confirm")
	public ModelAndView deleteCourseConfirm(@RequestParam int courseID) {
		_mav.setViewName("deleteCourseEvent.html");
		Course course = this.courseController.getByCourseId(courseID);
		_mav.addObject("course", course);
		_mav.addObject("undertitle","Confirm Deletion");
		return _mav;
	}
	
	@PostMapping(value="/deleteCourse/confirm",headers="Content-type=application/json")
	public String deleteCourseEvent(@RequestBody String source)
	{
		String[] par = source.split("\\=");
		int courseID = Integer.parseInt(par[1]);
		Course course = this.courseController.getByCourseId(courseID);
		this.courseController.delete(course);
		return "redirect:/courseList";
	}
	
	@RequestMapping("/studentEnroll")
	public ModelAndView studentEnrollMenu() {
		_mav.setViewName("studentEnroll.html");
		_mav.addObject("undertitle","Student Enroll Operation Menu");
		return _mav;
	}
	
	@GetMapping("/studentEnroll/add")
	public ModelAndView studentEnrollAdd(@RequestParam int studentID) {
		_mav.setViewName("studentEnrollment/addNewEnroll.html");
		EnrollmentData enrollmentData = new EnrollmentData();
		Student student = this.studentController.getByStudentID(studentID);
		System.out.println(student.get_firstName());
		List<Course> courses = this.courseController.get();
		List<Course> enrollments = this.enrollmentController.getByStudentID(studentID);
//		System.out.println(enrollments.get(0).get_courseName());
//		System.out.println(enrollments.get(0));
//		System.out.println(courses.get(0));
//		System.out.println(courses.get(0).equals(enrollments.get(0)));
		System.out.println(enrollments.contains(courses.get(0)));
		System.out.println(enrollments.contains(courses.get(2)));
		_mav.addObject("form",enrollmentData);
		_mav.addObject("student",student);
		_mav.addObject("courses",courses);
		_mav.addObject("enrollments",enrollments);
		_mav.addObject("undertitle","Enroll A Student");
		return _mav;
	}
	
	@PostMapping(value="/studentEnroll/add",headers="Content-type=application/json")
	public String studentEnrollAddEvent(@RequestParam("studentID") String studentIDString,@RequestBody String source) {
		this.enrollmentController.deleteByStudentID(Integer.parseInt(studentIDString));		
		int studentID = 0;
		ArrayList<Integer> courseIDs = new ArrayList<Integer>();
		String[] pars = source.split("\\&");
		for(String par : pars)
		{
			System.out.println(par);
			String[] par_pars = par.split("\\=");
			switch(par_pars[0])
			{
				case "studentID":
					studentID = Integer.parseInt(par_pars[1]); break;
				case "__courseIDs":
					courseIDs.add(Integer.parseInt(par_pars[1])); break;
			}
		}
		Student student = this.studentController.getByStudentID(studentID);
		System.out.println("Enrollment count: " + courseIDs.size());
		for(int courseID : courseIDs)
		{
			Course course = this.courseController.getByCourseId(courseID);
			Enrollment enrollment = new Enrollment();
			enrollment.set_studentID(studentID);
			enrollment.set_courseID(courseID);
			System.out.println(enrollment.get_courseID());
			student.set_tuitionBalance(student.get_tuitionBalance() - course.get_tuitionCost());
			this.studentController.update(student);
			this.enrollmentController.add(enrollment);
		}
		return "redirect:/studentList";
	}
	
//	public boolean isCourseEnrolled(List<Enrollment> enrollments, Course course) {
//	    return enrollments.stream().anyMatch(e -> e.get_courseID() == course.get_courseID());
//	}
	
	@GetMapping("/studentEnroll/discard")
	public ModelAndView studentEnrollDelete() {
		_mav.setViewName("studentEnrollment/deleteEnroll.html");
		_mav.addObject("undertitle","Delete Enrollment");
		return _mav;
	}
	
	@GetMapping("/studentEnroll/discard/menu")
	public ModelAndView studentEnrollDeleteMenu() {
		_mav.setViewName("studentEnrollment/deleteEnrollEvent.html");

		return _mav;
	}
}