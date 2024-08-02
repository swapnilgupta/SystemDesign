package com.cart;

// Concrete class for product
public class Item2 extends Product {

	public Item2(String name, double originalPrice, ProductType type) {
		super(name, originalPrice, type);
	}

	@Override
	public double getPrice() {
		return originalPrice;
	}

}
