package com.j01.StudentManagementWeb.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="Enrollment")
public class Enrollment {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _id;
	@Column(name="Fk_Student_Id")
	private int _Fk_Student_Id;
	@Column(name="Fk_Course_Id")
	private int _Fk_Course_Id;
	
	public Enrollment(int id, int studentID, int courseID) {
		super();
		this._id = id;
		this._Fk_Student_Id = studentID;
		this._Fk_Course_Id = courseID;
	}
	
	public Enrollment()
	{
		
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int id) {
		this._id = id;
	}

	public int get_studentID() {
		return _Fk_Student_Id;
	}

	public void set_studentID(int studentID) {
		this._Fk_Student_Id = studentID;
	}

	public int get_courseID() {
		return _Fk_Course_Id;
	}

	public void set_courseID(int courseID) {
		this._Fk_Course_Id = courseID;
	}
	
	
}
