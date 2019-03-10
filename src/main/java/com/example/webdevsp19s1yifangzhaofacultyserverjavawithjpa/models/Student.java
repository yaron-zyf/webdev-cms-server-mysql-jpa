package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity(name = "Students")
public class Student extends User {
	
	private float gpa;
	private Date dateOfBirth;
	
	public Student() {
	}
	
	public Student(String userName, String password, String firstName, String lastName, String email,
			String phone, String role, List<Course> courses) {
		super(userName, password, firstName, lastName, email, phone, role, courses);
	}
	
	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
}
