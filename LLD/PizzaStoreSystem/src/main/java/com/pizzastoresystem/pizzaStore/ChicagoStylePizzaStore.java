package com.pizzastoresystem.pizzaStore;

import com.pizzastoresystem.actors.Address;
import com.pizzastoresystem.pizza.ChicagoStyleCheesePizza;
import com.pizzastoresystem.pizza.ChicagoStyleVeggiePizza;
import com.pizzastoresystem.pizza.Pizza;

public class ChicagoStylePizzaStore extends PizzaStore {

	public ChicagoStylePizzaStore() {
		address = new Address("Chicago", "IL", "60616", "1234 S. Halsted St.");
	}

	public Pizza createPizza(String type) {
		if (type.equals("cheese")) {
			return new ChicagoStyleCheesePizza();
		} else if (type.equals("veggie")) {
			return new ChicagoStyleVeggiePizza();
		} else {
			return null;
		}
	}
}
