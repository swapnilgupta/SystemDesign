package com.pizzastoresystem.pizzaStore;

import com.pizzastoresystem.actors.Address;
import com.pizzastoresystem.pizza.NYStyleCheesePizza;
import com.pizzastoresystem.pizza.NYStyleVeggiePizza;
import com.pizzastoresystem.pizza.Pizza;

public class NYStylePizzaStore extends PizzaStore {

	public NYStylePizzaStore() {
		address = new Address("NY", "NY", "NY", "NY");
	}

	public Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new NYStyleCheesePizza();
		} else if (type.equals("veggie")) {
			return new NYStyleVeggiePizza();
		} else {
			return null;
		}
	}
}
