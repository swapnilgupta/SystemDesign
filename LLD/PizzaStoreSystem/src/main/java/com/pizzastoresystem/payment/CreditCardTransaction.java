package com.pizzastoresystem.payment;

public class CreditCardTransaction extends Payment {

	private String nameOnCard;

	@Override
	public void processPayment() {
		// Implement credit card processing logic
		System.out.println("Processing credit card payment for " + nameOnCard);
		setPaymentStatus(PaymentStatus.COMPLETED);
	}
}
