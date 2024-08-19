package com.pizzastoresystem.payment;

public class CashTransaction extends Payment {

	private double cashTendered;

	@Override
	public void processPayment() {
		// Implement cash payment logic
		System.out.println("Processing cash payment");
		setPaymentStatus(PaymentStatus.COMPLETED);
	}
}
