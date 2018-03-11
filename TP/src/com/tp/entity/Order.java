package com.tp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Commodity commodity;
	private Integer orderNo;
	private String name;
	private Double price;
	private Integer number;
	private String state;
	private Date time;
	private String remarks;
	private Set tradinghistories = new HashSet(0);
	private Set platforms = new HashSet(0);
	private Set delivers = new HashSet(0);
	private Set tradinghistories_1 = new HashSet(0);
	private Set delivers_1 = new HashSet(0);
	private Set platforms_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(Users users, Commodity commodity, Integer orderNo,
			String name, Double price, Integer number, String state, Date time,
			String remarks, Set tradinghistories, Set platforms, Set delivers,
			Set tradinghistories_1, Set delivers_1, Set platforms_1) {
		this.users = users;
		this.commodity = commodity;
		this.orderNo = orderNo;
		this.name = name;
		this.price = price;
		this.number = number;
		this.state = state;
		this.time = time;
		this.remarks = remarks;
		this.tradinghistories = tradinghistories;
		this.platforms = platforms;
		this.delivers = delivers;
		this.tradinghistories_1 = tradinghistories_1;
		this.delivers_1 = delivers_1;
		this.platforms_1 = platforms_1;
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

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public Set getTradinghistories() {
		return this.tradinghistories;
	}

	public void setTradinghistories(Set tradinghistories) {
		this.tradinghistories = tradinghistories;
	}

	public Set getPlatforms() {
		return this.platforms;
	}

	public void setPlatforms(Set platforms) {
		this.platforms = platforms;
	}

	public Set getDelivers() {
		return this.delivers;
	}

	public void setDelivers(Set delivers) {
		this.delivers = delivers;
	}

	public Set getTradinghistories_1() {
		return this.tradinghistories_1;
	}

	public void setTradinghistories_1(Set tradinghistories_1) {
		this.tradinghistories_1 = tradinghistories_1;
	}

	public Set getDelivers_1() {
		return this.delivers_1;
	}

	public void setDelivers_1(Set delivers_1) {
		this.delivers_1 = delivers_1;
	}

	public Set getPlatforms_1() {
		return this.platforms_1;
	}

	public void setPlatforms_1(Set platforms_1) {
		this.platforms_1 = platforms_1;
	}

}