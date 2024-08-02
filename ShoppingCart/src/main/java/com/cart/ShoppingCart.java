package com.cart;

import com.cart.coupon.PercentageCouponDecorator;
import com.cart.coupon.TypeCouponDecorator;
import java.util.List;

// Class for shopping cart
public class ShoppingCart {

	List<Product> productList;

	public ShoppingCart() {
	}

	// Add product to cart with eligible discount
	public void addToCart(Product product) {
		Product productWithEligibleDiscount = new TypeCouponDecorator(
			new PercentageCouponDecorator(product, 10), 3, product.getType());
		productList.add(productWithEligibleDiscount);
	}

	/**
	 * Method to get total price of products in cart
	 * @return total price of products in cart
	 */
	public double getTotalPrice() {
		double totalPrice = 0;
		for (Product product : productList) {
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}

}
