package edu.mum.asd.library.model;

public class Admin extends User {

	private UserRole role;

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Admin() {
		super();
		this.role = UserRole.ADMIN;
	}

	@Override
	public String toString() {
		return "Admin [role=" + role + "]";
	}

}
