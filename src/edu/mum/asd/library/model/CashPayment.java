package edu.mum.asd.library.model;

public class CashPayment implements PaymentStrategy{
	
	private double amount;

	public CashPayment(double amount) {
		super();
		this.amount = amount;
	}

	@Override
	public void pay() {
		System.out.println("Cash payment: $"+amount);
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
