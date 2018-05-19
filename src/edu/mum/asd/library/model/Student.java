package edu.mum.asd.library.model;

public class Student extends User {

	private UserRole role;
	private Librarian approvedBy;
	private boolean isEligible;

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Librarian getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Librarian approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isEligible() {
		return isEligible;
	}

	public void setEligible(boolean isEligible) {
		this.isEligible = isEligible;
	}

	public Student(Librarian approvedBy, boolean isEligible) {
		super();
		this.role = UserRole.STUDENT;
		this.approvedBy = approvedBy;
		this.isEligible = isEligible;
	}

	public Student() {
		super();
		this.role = UserRole.STUDENT;
	}

}
