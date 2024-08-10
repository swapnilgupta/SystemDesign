package org.example;

import com.cart.Item1;
import com.cart.Item2;
import com.cart.Product;
import com.cart.ProductType;
import com.cart.ShoppingCart;

// Main class to test the shopping cart
public class Main {

	public static void main(String[] args) {
		Product item1 = new Item1("FAN", 1000, ProductType.ELECTRONIC_GOODS);
		Product item2 = new Item2("TABLE", 2000, ProductType.FURNITURE_GOODS);

		ShoppingCart cart = new ShoppingCart();
		cart.addToCart(item1);
		cart.addToCart(item2);

		System.out.println("Total Price after discount: " + cart.getTotalPrice());
	}
}