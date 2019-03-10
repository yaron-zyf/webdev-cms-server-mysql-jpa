package com.example.webdevsp19s1yifangzhaofacultyserverjavawithjpa.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity(name = "Faculties")
public class Faculty extends User {
	
	private String department;
	private Date lastLoggedIn;
	
	public Faculty() {
	}

	public Faculty(String userName, String password, String firstName, String lastName, String email,
			String phone, String role, List<Course> courses) {
		super(userName, password, firstName, lastName, email, phone, role, courses);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}
	
}
