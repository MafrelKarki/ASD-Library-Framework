package edu.mum.asd.library.model;

public class CardPayment implements PaymentStrategy {

	private CardType cardType;
	private String cardNumber;
	private double amount;
	
	public CardPayment(CardType cardType, String cardNumber, double amount) {
		super();
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.amount = amount;
	}

	@Override
	public void pay() {
		System.out.println("Payment made using card payment");
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
