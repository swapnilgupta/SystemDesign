package com.pizzastoresystem.actors;

public class Person {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	protected String name;
	protected Address address;
	protected String phone;

	public Person(String name, Address address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}


}
