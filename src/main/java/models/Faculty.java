package models;

import java.util.Date;
import java.util.List;

public class Faculty extends User {
	
	private String department;
	private Date lastLoggedIn;
	
	public Faculty() {
	}

	public Faculty(int id, String userName, String password, String firstName, String lastName, String email,
			String phone, String role, List<Course> courses) {
		super(id, userName, password, firstName, lastName, email, phone, role, courses);
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
