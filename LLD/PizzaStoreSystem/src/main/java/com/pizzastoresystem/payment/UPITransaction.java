package com.pizzastoresystem.payment;

public class UPITransaction extends Payment {

	private String vpa;

	@Override
	public void processPayment() {
		// Implement UPI payment logic
		System.out.println("Processing UPI payment for VPA: " + vpa);
		setPaymentStatus(PaymentStatus.COMPLETED);
	}
}
