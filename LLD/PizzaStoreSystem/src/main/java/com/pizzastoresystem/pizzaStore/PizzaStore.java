package com.pizzastoresystem.pizzaStore;

import com.pizzastoresystem.actors.Address;
import com.pizzastoresystem.pizza.Pizza;
import java.util.List;

public abstract class PizzaStore {

	public Address address;
	protected List<Pizza> pizzas;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public abstract Pizza createPizza(String type);
}

