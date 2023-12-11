package com.j01.StudentManagementWeb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Student")
public class Student {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _id;
	@Column(name="studentID")
	private int _studentID;
	@Column(name="tuitionBalance")
	private int _tuitionBalance;
	@Column(name="firstName")
	private String _firstName;
	@Column(name="lastName")
	private String _lastName;
	@Column(name="gradeYear")
	private int _gradeYear;
	
	public Student(int id, int studentID, int tuitionBalance, String firstName, String lastName, int gradeYear) {
		super();
		this._id = id;
		this._studentID = studentID;
		this._tuitionBalance = tuitionBalance;
		this._firstName = firstName;
		this._lastName = lastName;
		this._gradeYear = gradeYear;
	}
	
	public Student()
	{
		
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int id) {
		this._id = id;
	}

	public int get_studentID() {
		return _studentID;
	}

	public void set_studentID(int studentID) {
		this._studentID = studentID;
	}

	public int get_tuitionBalance() {
		return _tuitionBalance;
	}

	public void set_tuitionBalance(int tuitionBalance) {
		this._tuitionBalance = tuitionBalance;
	}

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String firstName) {
		this._firstName = firstName;
	}

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String lastName) {
		this._lastName = lastName;
	}

	public int get_gradeYear() {
		return _gradeYear;
	}

	public void set_gradeYear(int gradeYear) {
		this._gradeYear = gradeYear;
	}
	
}
