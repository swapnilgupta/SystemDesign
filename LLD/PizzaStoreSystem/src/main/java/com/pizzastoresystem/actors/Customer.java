package com.pizzastoresystem.actors;

import com.pizzastoresystem.order.Order;
import com.pizzastoresystem.order.OrderStatus;
import com.pizzastoresystem.pizza.Pizza;
import com.pizzastoresystem.pizzaStore.PizzaStore;
import java.util.List;

public class Customer extends Person {

	private String customerID;
	private List<PizzaStore> stores;
	private List<Pizza> selectedPizzas;

	public Customer(String customerID, String name, Address address, String phone) {
		super(name, address, phone);
		this.customerID = customerID;
	}

	public Order placeOrder(PizzaStore store, String pizzaType) {
		Pizza pizza = store.createPizza(pizzaType);
		selectedPizzas.add(pizza);
		return new Order(customerID, selectedPizzas, null, null, OrderStatus.RECEIVED);
	}

	// Getters and setters
}
