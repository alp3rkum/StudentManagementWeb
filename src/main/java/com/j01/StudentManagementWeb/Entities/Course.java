package com.j01.StudentManagementWeb.Entities;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="Course")
public class Course {
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _id;
	@Column(name="courseID")
	private int _courseID;
	@Column(name="courseName")
	private String _courseName;
	@Column(name="gradeYear")
	private int _gradeYear;
	@Column(name="tuitionCost")
	private int _tuitionCost;
	
	public Course(int id, int courseID, String courseName, int gradeYear, int tuitionCost) {
		super();
		this._id = id;
		this._courseID = courseID;
		this._courseName = courseName;
		this._gradeYear = gradeYear;
		this._tuitionCost = tuitionCost;
	}
	
	public Course() {
		
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int id) {
		this._id = id;
	}

	public int get_courseID() {
		return _courseID;
	}

	public void set_courseID(int courseID) {
		this._courseID = courseID;
	}

	public String get_courseName() {
		return _courseName;
	}

	public void set_courseName(String courseName) {
		this._courseName = courseName;
	}

	public int get_gradeYear() {
		return _gradeYear;
	}

	public void set_gradeYear(int gradeYear) {
		this._gradeYear = gradeYear;
	}

	public int get_tuitionCost() {
		return _tuitionCost;
	}

	public void set_tuitionCost(int _tuitionCost) {
		this._tuitionCost = _tuitionCost;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(_courseID, course._courseID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_courseID);
    }
}
