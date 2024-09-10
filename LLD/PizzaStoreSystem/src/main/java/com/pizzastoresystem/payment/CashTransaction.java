package com.pizzastoresystem.payment;

public class CashTransaction extends Payment {

	private double cashTendered;
	CashTransaction(double cashAmount) {
		this.cashTendered = cashAmount;
	}

	@Override
	public void processPayment(String name) {
		// Implement cash payment logic
		System.out.println("Processing cash payment: " + name);
		setPaymentStatus(PaymentStatus.COMPLETED);
	}
}
