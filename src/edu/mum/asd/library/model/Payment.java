package edu.mum.asd.library.model;

import java.util.Date;

public class Payment {
	private Loan loan;
	private double amount;
	private String paymentMode;
	private Date payedOn;

	public Payment() {}
	
	public Payment(Loan loan, double amount, String paymentMode) {
		super();
		this.loan = loan;
		this.amount = amount;
		this.paymentMode=paymentMode;
	}
	
	public Payment(Loan loan, double amount, String paymentMode, Date payedOn) {
		super();
		this.loan = loan;
		this.amount = amount;
		this.paymentMode=paymentMode;
		this.payedOn=payedOn;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getPayedOn() {
		return payedOn;
	}

	public void setPayedOn(Date payedOn) {
		this.payedOn = payedOn;
	}

}
