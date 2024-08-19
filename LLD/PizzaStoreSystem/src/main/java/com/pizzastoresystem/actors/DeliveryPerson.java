package com.pizzastoresystem.actors;

import com.pizzastoresystem.order.Order;
import com.pizzastoresystem.order.OrderStatus;

public class DeliveryPerson extends Person {

	public DeliveryPerson(String name, Address address, String phone) {
		super(name, address, phone);
	}

	public void deliverPizza() {
		System.out.println(
			"Delivering pizza to " + address.getStreet() + ", " + address.getCity() + ", "
				+ address.getState() + " ");
	}

	public OrderStatus deliverOrder(Order order) {
		if (order.getStatus() == OrderStatus.DELIVERED) {
			System.out.println("Order already delivered");
			return OrderStatus.DELIVERED;
		}
		order.setStatus(OrderStatus.DELIVERED);
		System.out.println("Order delivered");
		return OrderStatus.DELIVERED;

	}
}
