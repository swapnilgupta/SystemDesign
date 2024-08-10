package org.example;


import java.util.ArrayList;
import java.util.List;

/**
 * Coupon can give minimum Z% off upto X amount (with or without min cart size) -- done
 * Coupon can give a flat discount of X (with or without min cart size)
 * Coupon can be applicable for one/few/all customers
 * Coupon can be applicable on one/few/all merchants
 * Coupon can be used only one time/few time/everytime.
 *
 */

class Customer {

    private int customerId;
    private String name;
    private String email;

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
}

class Merchant {

    private int merchantId;
    private String name;
    private String address;

    public Merchant(int merchantId, String name, String address) {
        this.merchantId = merchantId;
        this.name = name;
        this.address = address;
    }

    // Getters and setters
}

class Coupon {

    private int couponId;
    private String code;
    private double discountValue;
    private double minCartSize;
    private boolean isPercentageDiscount;
    private int maxUsageCount;
    private int currentUsageCount;
    private int percentage;
    private List<Customer> applicableCustomers;
    private List<Merchant> applicableMerchants;
    private boolean isApplicableAlways;


    public Coupon(int couponId, String code, double discountValue, double minCartSize,
        boolean isPercentageDiscount, int maxUsageCount, int percentage, boolean isApplicableAlways) {
        couponId = couponId;
        this.code = code;
        this.discountValue = discountValue;
        this.minCartSize = minCartSize;
        this.isPercentageDiscount = isPercentageDiscount;
        this.maxUsageCount = maxUsageCount;
        this.currentUsageCount = 0;
        this.applicableCustomers = new ArrayList<>();
        this.applicableMerchants = new ArrayList<>();
        this.percentage = percentage;
        this.isApplicableAlways = isApplicableAlways;
    }

    public void addApplicableCustomer(Customer customer) {
        applicableCustomers.add(customer);
    }

    public void addApplicableMerchant(Merchant merchant) {
        applicableMerchants.add(merchant);
    }

    public boolean isApplicableToCustomer(Customer customer) {
        return applicableCustomers.contains(customer);
    }

    public boolean isApplicableToMerchant(Merchant merchant) {
        return applicableMerchants.contains(merchant);
    }

    public boolean isExpired() {
        // Implement logic to check if the coupon is expired (based on dates)
        return currentUsageCount > maxUsageCount;
    }

    public boolean isUsable() {
        return currentUsageCount < maxUsageCount;
    }

    public double calculateDiscount(double cartTotal) {
        if (cartTotal < minCartSize) {
            return 0; // Coupon is not applicable due to cart size
        }
        double discount = 0;
        if (isPercentageDiscount) {
            discount = cartTotal * ((double) percentage / 100);
        } else {
            discount = Math.min(discountValue, cartTotal);
        }
        return Math.min(discount, discountValue); // Cap with discountValue
    }

    public void incrementUsage() {
        currentUsageCount++;
        if(currentUsageCount > maxUsageCount) {
            System.out.println("Coupon usage limit exceeded.");
        }
    }

    public void addCoupons(Coupon coupon) {
        // Add coupon logic

    }

    // Getters and setters
}

class Cart {

    private List<Product> items;
    private Customer customer;
    private Merchant merchant;
    private double cartTotal;

    public Cart(Customer customer, Merchant merchant) {
        this.customer = customer;
        this.merchant = merchant;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
        cartTotal += product.getPrice();
    }

    public void removeItem(Product product) {
        items.remove(product);
        cartTotal -= product.getPrice();
    }


    public void applyCoupon(Coupon coupon) {
        double discount = 0.0;
        if (coupon.isApplicableToCustomer(customer) && coupon.isApplicableToMerchant(merchant)
            && coupon.isUsable() && !coupon.isExpired()) {
             discount = coupon.calculateDiscount(this.cartTotal);
             this.cartTotal = this.cartTotal - discount;
            // Apply the discount to the cart
            System.out.println("Coupon applied. Discount: " + discount);
            coupon.incrementUsage();
        } else {
            System.out.println("Coupon not applicable.");
        }
    }

    public double checkout() {
        return this.cartTotal;
    }
}

class Product {

    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(1, "John Doe", "john@example.com");
        Merchant merchant = new Merchant(1, "Sample Merchant", "123 Main St");

        Coupon percentageCoupon = new Coupon(1, "SAVE10", 10, 50, true,
            1, 10, true);
        percentageCoupon.addApplicableCustomer(customer);
        percentageCoupon.addApplicableMerchant(merchant);

        Coupon flatCouponAlways = new Coupon(1, "FLAT20", 10, 50, false,
            100, 10, true);
        flatCouponAlways.addApplicableCustomer(customer);
        flatCouponAlways.addApplicableMerchant(merchant);

        Coupon flatCouponFew = new Coupon(1, "FLAT20", 10, 50, false,
            2, 10, true);
        flatCouponFew.addApplicableCustomer(customer);
        flatCouponFew.addApplicableMerchant(merchant);

        Product product1 = new Product(1, "Product A", 25);
        Product product2 = new Product(2, "Product B", 30);

        Cart cart = new Cart(customer, merchant);
        cart.addItem(product1);
        cart.addItem(product2);

        // applying 2 times
        cart.applyCoupon(percentageCoupon);
        double total = cart.checkout();
        System.out.println("Final Total: " + total);

        cart.applyCoupon(percentageCoupon);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

        // applying 3 times
        cart.applyCoupon(flatCouponAlways);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

        cart.applyCoupon(flatCouponAlways);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

        cart.applyCoupon(flatCouponAlways);
        total = cart.checkout();
        System.out.println("Final Total: " + total);


        // applying 3 times
        cart.applyCoupon(flatCouponFew);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

        cart.applyCoupon(flatCouponFew);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

        cart.applyCoupon(flatCouponFew);
        total = cart.checkout();
        System.out.println("Final Total: " + total);

    }
}
