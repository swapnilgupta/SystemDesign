package com.pizzastoresystem;

import com.pizzastoresystem.actors.Address;
import com.pizzastoresystem.actors.Customer;
import com.pizzastoresystem.actors.DeliveryPerson;
import com.pizzastoresystem.order.Order;
import com.pizzastoresystem.order.OrderStatus;
import com.pizzastoresystem.payment.CashTransaction;
import com.pizzastoresystem.payment.CreditCardTransaction;
import com.pizzastoresystem.payment.Payment;
import com.pizzastoresystem.pizzaStore.ChicagoStylePizzaStore;
import com.pizzastoresystem.pizzaStore.NYStylePizzaStore;
import com.pizzastoresystem.pizzaStore.PizzaStore;

public class PizzaTestDrive {
	public static void main(String[] args) {
		// Create PizzaStores for New York and Chicago
		PizzaStore nyStore = new NYStylePizzaStore();
		PizzaStore chicagoStore = new ChicagoStylePizzaStore();

		// Create customer
		Address customerAddress = new Address("123 Main St", "New York", "NY", "10001");
		Customer ethan = new Customer("ETH001", "Ethan", customerAddress, "123-456-7890");

		// Ethan orders a cheese pizza from a New York Pizza Store
		Order nyOrder = ethan.placeOrder(nyStore, "cheese");
		System.out.println("Ethan ordered a " + nyOrder.getPizzaList().getFirst().getName() + " from New York Pizza Store");

		// Processing payment for the order
		Payment nyPayment = new CreditCardTransaction();
		nyPayment.processPayment();
		nyOrder.setPaymentDetails(nyPayment);

		// Assign delivery person
		Address deliveryAddress = new Address("456 Broadway", "New York", "NY", "10012");
		DeliveryPerson john = new DeliveryPerson("John", deliveryAddress, "987-654-3210");
		nyOrder.setDeliveryPerson(john);

		// John delivers the order
		OrderStatus nyOrderStatus = john.deliverOrder(nyOrder);
		System.out.println("Order delivered with status: " + nyOrderStatus);

		// Joel orders a cheese pizza from Chicago Pizza Store
		Order chicagoOrder = ethan.placeOrder(chicagoStore, "cheese");
		System.out.println("Joel ordered a " + chicagoOrder.getPizzaList().getFirst().getName() + " from Chicago Pizza Store");

		// Processing payment for the order
		Payment chicagoPayment = new CashTransaction();
		chicagoPayment.processPayment();
		chicagoOrder.setPaymentDetails(chicagoPayment);

		// Assign delivery person
		DeliveryPerson mike = new DeliveryPerson("Mike", deliveryAddress, "321-654-9870");
		chicagoOrder.setDeliveryPerson(mike);

		// Mike delivers the order
		OrderStatus chicagoOrderStatus = mike.deliverOrder(chicagoOrder);
		System.out.println("Order delivered with status: " + chicagoOrderStatus);
	}
}