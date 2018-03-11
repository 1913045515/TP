package com.tp.entity;

import java.util.Date;

/**
 * Platform entity. @author MyEclipse Persistence Tools
 */

public class Platform implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByReceivablesId;
	private Order order;
	private Users usersByPaymentId;
	private Double price;
	private Date completeTime;
	private Date invalidTime;

	// Constructors

	/** default constructor */
	public Platform() {
	}

	/** full constructor */
	public Platform(Users usersByReceivablesId, Order order,
			Users usersByPaymentId, Double price, Date completeTime,
			Date invalidTime) {
		this.usersByReceivablesId = usersByReceivablesId;
		this.order = order;
		this.usersByPaymentId = usersByPaymentId;
		this.price = price;
		this.completeTime = completeTime;
		this.invalidTime = invalidTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByReceivablesId() {
		return this.usersByReceivablesId;
	}

	public void setUsersByReceivablesId(Users usersByReceivablesId) {
		this.usersByReceivablesId = usersByReceivablesId;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Users getUsersByPaymentId() {
		return this.usersByPaymentId;
	}

	public void setUsersByPaymentId(Users usersByPaymentId) {
		this.usersByPaymentId = usersByPaymentId;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getInvalidTime() {
		return this.invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

}