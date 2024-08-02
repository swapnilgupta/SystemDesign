package com.cart.coupon;

import com.cart.Product;
import com.cart.ProductType;

public class PercentageCouponDecorator extends CouponDecorator {

	private final double percentage;
	private final Product product;

	public PercentageCouponDecorator(Product product, double percentage) {
		this.product = product;
		this.percentage = percentage;
	}

	@Override
	public double getPrice() {
		return product.getPrice() * (1 - percentage / 100);
	}

	public double getPercentage() {
		return percentage;
	}

	public Product getProduct() {
		return product;
	}


	@Override
	public ProductType getType() {
		return product.getType();
	}

}
