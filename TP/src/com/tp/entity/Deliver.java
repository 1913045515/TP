package com.tp.entity;

/**
 * Deliver entity. @author MyEclipse Persistence Tools
 */

public class Deliver implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Order order;
	private String information;
	private String address;
	private String remarks;

	// Constructors

	/** default constructor */
	public Deliver() {
	}

	/** full constructor */
	public Deliver(Users users, Order order, String information,
			String address, String remarks) {
		this.users = users;
		this.order = order;
		this.information = information;
		this.address = address;
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

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}