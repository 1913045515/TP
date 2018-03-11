package com.tp.entity;

import java.util.Date;

/**
 * Tradinghistory entity. @author MyEclipse Persistence Tools
 */

public class Tradinghistory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Order order;
	private Double price;
	private String state;
	private Date time;
	private String remarks;

	// Constructors

	/** default constructor */
	public Tradinghistory() {
	}

	/** full constructor */
	public Tradinghistory(Users users, Order order, Double price, String state,
			Date time, String remarks) {
		this.users = users;
		this.order = order;
		this.price = price;
		this.state = state;
		this.time = time;
		this.remarks = remarks;
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

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}