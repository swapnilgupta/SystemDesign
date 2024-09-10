package com.pizzastoresystem.payment;

public class CreditCardTransaction extends Payment {

	private String nameOnCard;

	@Override
	public void processPayment(String nameOnCard) {
		setNameOnCard(nameOnCard);
		// Implement credit card processing logic
		System.out.println("Processing credit card payment for " + nameOnCard);
		setPaymentStatus(PaymentStatus.COMPLETED);
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
}
