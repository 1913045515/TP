package com.tp.entity;

/**
 * Usersamount entity. @author MyEclipse Persistence Tools
 */

public class Usersamount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Double price;
	private String amount;

	// Constructors

	/** default constructor */
	public Usersamount() {
	}

	/** full constructor */
	public Usersamount(Users users, Double price, String amount) {
		this.users = users;
		this.price = price;
		this.amount = amount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}