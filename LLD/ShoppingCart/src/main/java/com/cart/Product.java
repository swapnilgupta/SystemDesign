package com.cart;

// Abstract class for product
public abstract class Product {

	String name;
	double originalPrice;
	ProductType type;

	// parameter less constructor
	public Product() {
	}

	Product(String name, double originalPrice, ProductType type) {
		this.name = name;
		this.originalPrice = originalPrice;
		this.type = type;
	}

	public abstract double getPrice();

	public ProductType getType() {
		return type;
	}

}
