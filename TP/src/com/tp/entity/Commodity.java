package com.tp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Commodity entity. @author MyEclipse Persistence Tools
 */

public class Commodity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Commoditypath commoditypath;
	private Users users;
	private Classification classification;
	private Campus campus;
	private String name;
	private Double originalPrice;
	private Double price;
	private Integer number;
	private String degree;
	private String describes;
	private Date editTimes;
	private Date releaseTimes;
	private Integer shelfState;
	private Set commoditypaths = new HashSet(0);
	private Set commoditypaths_1 = new HashSet(0);
	private Set punishments = new HashSet(0);
	private Set punishments_1 = new HashSet(0);
	private Set reports = new HashSet(0);
	private Set reports_1 = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set promotioncommodities = new HashSet(0);
	private Set orders_1 = new HashSet(0);
	private Set promotioncommodities_1 = new HashSet(0);
	private Set comments_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Commodity() {
	}

	/** full constructor */
	public Commodity(Commoditypath commoditypath, Users users,
			Classification classification, Campus campus, String name,
			Double originalPrice, Double price, Integer number, String degree,
			String describes, Date editTimes, Date releaseTimes,
			Integer shelfState, Set commoditypaths, Set commoditypaths_1,
			Set punishments, Set punishments_1, Set reports, Set reports_1,
			Set orders, Set comments, Set promotioncommodities, Set orders_1,
			Set promotioncommodities_1, Set comments_1) {
		this.commoditypath = commoditypath;
		this.users = users;
		this.classification = classification;
		this.campus = campus;
		this.name = name;
		this.originalPrice = originalPrice;
		this.price = price;
		this.number = number;
		this.degree = degree;
		this.describes = describes;
		this.editTimes = editTimes;
		this.releaseTimes = releaseTimes;
		this.shelfState = shelfState;
		this.commoditypaths = commoditypaths;
		this.commoditypaths_1 = commoditypaths_1;
		this.punishments = punishments;
		this.punishments_1 = punishments_1;
		this.reports = reports;
		this.reports_1 = reports_1;
		this.orders = orders;
		this.comments = comments;
		this.promotioncommodities = promotioncommodities;
		this.orders_1 = orders_1;
		this.promotioncommodities_1 = promotioncommodities_1;
		this.comments_1 = comments_1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Commoditypath getCommoditypath() {
		return this.commoditypath;
	}

	public void setCommoditypath(Commoditypath commoditypath) {
		this.commoditypath = commoditypath;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Classification getClassification() {
		return this.classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public Campus getCampus() {
		return this.campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
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

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDescribes() {
		return this.describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public Date getEditTimes() {
		return this.editTimes;
	}

	public void setEditTimes(Date editTimes) {
		this.editTimes = editTimes;
	}

	public Date getReleaseTimes() {
		return this.releaseTimes;
	}

	public void setReleaseTimes(Date releaseTimes) {
		this.releaseTimes = releaseTimes;
	}

	public Integer getShelfState() {
		return this.shelfState;
	}

	public void setShelfState(Integer shelfState) {
		this.shelfState = shelfState;
	}

	public Set getCommoditypaths() {
		return this.commoditypaths;
	}

	public void setCommoditypaths(Set commoditypaths) {
		this.commoditypaths = commoditypaths;
	}

	public Set getCommoditypaths_1() {
		return this.commoditypaths_1;
	}

	public void setCommoditypaths_1(Set commoditypaths_1) {
		this.commoditypaths_1 = commoditypaths_1;
	}

	public Set getPunishments() {
		return this.punishments;
	}

	public void setPunishments(Set punishments) {
		this.punishments = punishments;
	}

	public Set getPunishments_1() {
		return this.punishments_1;
	}

	public void setPunishments_1(Set punishments_1) {
		this.punishments_1 = punishments_1;
	}

	public Set getReports() {
		return this.reports;
	}

	public void setReports(Set reports) {
		this.reports = reports;
	}

	public Set getReports_1() {
		return this.reports_1;
	}

	public void setReports_1(Set reports_1) {
		this.reports_1 = reports_1;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getPromotioncommodities() {
		return this.promotioncommodities;
	}

	public void setPromotioncommodities(Set promotioncommodities) {
		this.promotioncommodities = promotioncommodities;
	}

	public Set getOrders_1() {
		return this.orders_1;
	}

	public void setOrders_1(Set orders_1) {
		this.orders_1 = orders_1;
	}

	public Set getPromotioncommodities_1() {
		return this.promotioncommodities_1;
	}

	public void setPromotioncommodities_1(Set promotioncommodities_1) {
		this.promotioncommodities_1 = promotioncommodities_1;
	}

	public Set getComments_1() {
		return this.comments_1;
	}

	public void setComments_1(Set comments_1) {
		this.comments_1 = comments_1;
	}

}