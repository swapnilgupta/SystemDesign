package org.example;


import java.util.ArrayList;
import java.util.List;

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
    private List<Customer> applicableCustomers;
    private List<Merchant> applicableMerchants;

    public Coupon(int couponId, String code, double discountValue, double minCartSize,
        boolean isPercentageDiscount, int maxUsageCount) {
        couponId = couponId;
        this.code = code;
        this.discountValue = discountValue;
        this.minCartSize = minCartSize;
        this.isPercentageDiscount = isPercentageDiscount;
        this.maxUsageCount = maxUsageCount;
        this.currentUsageCount = 0;
        this.applicableCustomers = new ArrayList<>();
        this.applicableMerchants = new ArrayList<>();
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
        return false;
    }

    public boolean isUsable() {
        return currentUsageCount < maxUsageCount;
    }

    public double calculateDiscount(double cartTotal) {
        if (cartTotal < minCartSize) {
            return 0; // Coupon is not applicable due to cart size
        }

        if (isPercentageDiscount) {
            return cartTotal * (discountValue / 100);
        } else {
            return Math.min(discountValue, cartTotal);
        }
    }

    public void incrementUsage() {
        currentUsageCount++;
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

    public Cart(Customer customer, Merchant merchant) {
        this.customer = customer;
        this.merchant = merchant;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void applyCoupon(Coupon coupon) {
        if (coupon.isApplicableToCustomer(customer) && coupon.isApplicableToMerchant(merchant)
            && coupon.isUsable() && !coupon.isExpired()) {
            double cartTotal = calculateTotal();
            double discount = coupon.calculateDiscount(cartTotal);
            // Apply the discount to the cart
            // (In a real system, you would apply this discount to the total price)
            System.out.println("Coupon applied. Discount: " + discount);
            coupon.incrementUsage();
        } else {
            System.out.println("Coupon not applicable.");
        }
    }

    public double checkout() {
        double total = calculateTotal();
        // Apply any additional discounts or processes before returning the final amount
        return total;
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

        Coupon coupon = new Coupon(1, "SAVE10", 10, 50, true, 100);

        Product product1 = new Product(1, "Product A", 25);
        Product product2 = new Product(2, "Product B", 30);

        Cart cart = new Cart(customer, merchant);
        cart.addItem(product1);
        cart.addItem(product2);

        cart.applyCoupon(coupon);
        double total = cart.checkout();
        System.out.println("Final Total: " + total);
    }
}
