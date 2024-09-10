package com.pizzastoresystem.order;

import com.pizzastoresystem.actors.Address;
import com.pizzastoresystem.actors.DeliveryPerson;
import com.pizzastoresystem.payment.Payment;
import com.pizzastoresystem.pizza.Pizza;
import java.util.List;


public class Order {

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public void setPizzaList(List<Pizza> pizzaList) {
		this.pizzaList = pizzaList;
	}

	public DeliveryPerson getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public Payment getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(Payment paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	private String customerID;
	private List<Pizza> pizzaList;
	private DeliveryPerson deliveryPerson;
	private Payment paymentDetails;
	private OrderStatus status;

	public Order(String customerID, List<Pizza> pizzaList, Payment paymentDetails, OrderStatus status) {
		this.customerID = customerID;
		this.pizzaList = pizzaList;
		this.deliveryPerson = assignDeliveryPerson();
		this.paymentDetails = paymentDetails;
		this.status = status;
	}

	private DeliveryPerson assignDeliveryPerson() {
		// Assign a delivery person to the order
		Address address = new Address("123 Main St", "Springfield", "IL", "62701");
		return new DeliveryPerson("John Doe", address, "1234567890");
	}

	// Getters and setters
}