package edu.mum.asd.library.model;

import java.sql.Date;

public class Loan {
	private String callNo;
	private Date issuedDate;
	private Date returnDate;
	private String returnStatus;
	private Student student;
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Loan(String callNo, Student student) {
		super();
		this.callNo = callNo;
		this.issuedDate = new Date(0);
		this.returnDate = null;
		this.returnStatus = "no";
		this.student = student;
	}
	public Loan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Loan [callNo=" + callNo + ", issuedDate=" + issuedDate + ", returnDate=" + returnDate
				+ ", returnStatus=" + returnStatus + ", student=" + student + "]";
	}

	
	
	
}
