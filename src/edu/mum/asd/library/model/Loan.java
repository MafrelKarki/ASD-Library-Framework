package edu.mum.asd.library.model;


import java.util.Calendar;
import java.util.Date;


public class Loan {
	private int id;
	private String callNo;
	private Date issuedDate;
	private Date returnDate;
	private String returnStatus;
	private Student student;
	private ItemState currentState;
	
	public double computeLoan() {
		final double hourlyFine=5;
		long numberOfDays=(new Date().getTime()-returnDate.getTime())/1000/60/60/24;
		System.out.println(numberOfDays);
		return hourlyFine*Math.round(numberOfDays);
	}
	
	public int getExcessDays() {
		return Math.round((new Date().getTime()-returnDate.getTime())/1000/60/60/24);
	}
	
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
		this.issuedDate = new Date();

		Calendar c = Calendar.getInstance(); 
		c.setTime(issuedDate); 
		c.add(Calendar.MONTH, +1);		 
		this.returnDate =c.getTime();
//		this.returnDate = newDate;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public ItemState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ItemState currentState) {
		this.currentState = currentState;
	}

		
}
