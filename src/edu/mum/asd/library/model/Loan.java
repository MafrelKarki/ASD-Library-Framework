package edu.mum.asd.library.model;


import java.util.Calendar;
import java.util.Date;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


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
		Date newDate = DateUtils.addMonths(new Date(), 1);
		
		this.callNo = callNo;
		this.issuedDate = new Date();

		Calendar c = Calendar.getInstance(); 
		c.setTime(issuedDate); 
		c.add(Calendar.MONTH, +1);		 
		this.returnDate =c.getTime();
		this.returnDate = newDate;
		this.returnStatus = "no";
		this.student = student;
	}
	public Loan() {
		super();
	}
	
	@Override
	public String toString() {
		return "Loan [callNo=" + callNo + ", issuedDate=" + issuedDate + ", returnDate=" + returnDate
				+ ", returnStatus=" + returnStatus + ", student=" + student + "]";
	}

	
	
	
}
