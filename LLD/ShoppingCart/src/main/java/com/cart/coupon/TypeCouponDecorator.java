package com.cart.coupon;

import com.cart.Product;
import com.cart.ProductType;
import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends CouponDecorator {

	private final Product product;
	private final ProductType type;
	private final double discountPercentage;

	static List<ProductType> eligibleTypes = new ArrayList<>();

	static {
		eligibleTypes.add(ProductType.FURNITURE_GOODS);
		eligibleTypes.add(ProductType.DECORATIVE_GOODS);
	}

	public TypeCouponDecorator(Product product, double discountPercentage, ProductType type) {
		this.product = product;
		this.type = type;
		this.discountPercentage = discountPercentage;
	}

	@Override
	public double getPrice() {
		if (eligibleTypes.contains(type)) {
			return product.getPrice() * (1 - discountPercentage / 100);
		}
		return product.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public ProductType getType() {
		return type;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}


}
