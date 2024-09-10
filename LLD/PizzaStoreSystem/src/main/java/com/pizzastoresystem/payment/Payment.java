package com.pizzastoresystem.payment;

import java.util.Date;

public class Payment {

	private int paymentID;

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
		System.out.println("Payment status set to: " + paymentStatus);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	private double amount;
	private PaymentStatus paymentStatus;
	private Date creationDate;

	public void processPayment(String nameOnCard) {
		// Implement payment processing logic
	}
}

