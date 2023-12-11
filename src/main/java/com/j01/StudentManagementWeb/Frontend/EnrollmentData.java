package com.j01.StudentManagementWeb.Frontend;

import java.util.ArrayList;

public class EnrollmentData {
	private int _studentID;
	private ArrayList<Integer> _courseIDs;
	

	public EnrollmentData(int studentID, ArrayList<Integer> courseIDs) {
		//super();
		this._studentID = studentID;
		this._courseIDs = courseIDs;
	}
	
	public EnrollmentData()
	{
		
	}

	public int get_studentID() {
		return _studentID;
	}

	public void set_studentID(int studentID) {
		this._studentID = studentID;
	}

	public ArrayList<Integer> get_courseIDs() {
		return _courseIDs;
	}

	public void addNewCourse(int courseID) {
		this._courseIDs.add(courseID);
	}
	
	
}
