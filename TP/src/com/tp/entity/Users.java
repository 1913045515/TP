package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private Campus campus;
	private Jurisdiction jurisdiction;
	private String nickName;
	private String password;
	private String phone;
	private String acount;
	private String address;
	private Set punishmentsForUsersId = new HashSet(0);
	private Set punishmentsForHandleId = new HashSet(0);
	private Set changes = new HashSet(0);
	private Set commodities = new HashSet(0);
	private Set platformsForReceivablesId = new HashSet(0);
	private Set delivers = new HashSet(0);
	private Set orders = new HashSet(0);
	private Set commentsForObjectId = new HashSet(0);
	private Set platformsForPaymentId = new HashSet(0);
	private Set punishmentsForHandleId_1 = new HashSet(0);
	private Set orders_1 = new HashSet(0);
	private Set reports = new HashSet(0);
	private Set tradinghistories = new HashSet(0);
	private Set commentsForUsersId = new HashSet(0);
	private Set reports_1 = new HashSet(0);
	private Set commodities_1 = new HashSet(0);
	private Set usersamounts = new HashSet(0);
	private Set delivers_1 = new HashSet(0);
	private Set platformsForReceivablesId_1 = new HashSet(0);
	private Set changes_1 = new HashSet(0);
	private Set platformsForPaymentId_1 = new HashSet(0);
	private Set punishmentsForUsersId_1 = new HashSet(0);
	private Set usersamounts_1 = new HashSet(0);
	private Set tradinghistories_1 = new HashSet(0);
	private Set commentsForObjectId_1 = new HashSet(0);
	private Set commentsForUsersId_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Campus campus, Jurisdiction jurisdiction, String nickName,
			String password, String phone, String acount, String address,
			Set punishmentsForUsersId, Set punishmentsForHandleId, Set changes,
			Set commodities, Set platformsForReceivablesId, Set delivers,
			Set orders, Set commentsForObjectId, Set platformsForPaymentId,
			Set punishmentsForHandleId_1, Set orders_1, Set reports,
			Set tradinghistories, Set commentsForUsersId, Set reports_1,
			Set commodities_1, Set usersamounts, Set delivers_1,
			Set platformsForReceivablesId_1, Set changes_1,
			Set platformsForPaymentId_1, Set punishmentsForUsersId_1,
			Set usersamounts_1, Set tradinghistories_1,
			Set commentsForObjectId_1, Set commentsForUsersId_1) {
		this.campus = campus;
		this.jurisdiction = jurisdiction;
		this.nickName = nickName;
		this.password = password;
		this.phone = phone;
		this.acount = acount;
		this.address = address;
		this.punishmentsForUsersId = punishmentsForUsersId;
		this.punishmentsForHandleId = punishmentsForHandleId;
		this.changes = changes;
		this.commodities = commodities;
		this.platformsForReceivablesId = platformsForReceivablesId;
		this.delivers = delivers;
		this.orders = orders;
		this.commentsForObjectId = commentsForObjectId;
		this.platformsForPaymentId = platformsForPaymentId;
		this.punishmentsForHandleId_1 = punishmentsForHandleId_1;
		this.orders_1 = orders_1;
		this.reports = reports;
		this.tradinghistories = tradinghistories;
		this.commentsForUsersId = commentsForUsersId;
		this.reports_1 = reports_1;
		this.commodities_1 = commodities_1;
		this.usersamounts = usersamounts;
		this.delivers_1 = delivers_1;
		this.platformsForReceivablesId_1 = platformsForReceivablesId_1;
		this.changes_1 = changes_1;
		this.platformsForPaymentId_1 = platformsForPaymentId_1;
		this.punishmentsForUsersId_1 = punishmentsForUsersId_1;
		this.usersamounts_1 = usersamounts_1;
		this.tradinghistories_1 = tradinghistories_1;
		this.commentsForObjectId_1 = commentsForObjectId_1;
		this.commentsForUsersId_1 = commentsForUsersId_1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Campus getCampus() {
		return this.campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	public Jurisdiction getJurisdiction() {
		return this.jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAcount() {
		return this.acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set getPunishmentsForUsersId() {
		return this.punishmentsForUsersId;
	}

	public void setPunishmentsForUsersId(Set punishmentsForUsersId) {
		this.punishmentsForUsersId = punishmentsForUsersId;
	}

	public Set getPunishmentsForHandleId() {
		return this.punishmentsForHandleId;
	}

	public void setPunishmentsForHandleId(Set punishmentsForHandleId) {
		this.punishmentsForHandleId = punishmentsForHandleId;
	}

	public Set getChanges() {
		return this.changes;
	}

	public void setChanges(Set changes) {
		this.changes = changes;
	}

	public Set getCommodities() {
		return this.commodities;
	}

	public void setCommodities(Set commodities) {
		this.commodities = commodities;
	}

	public Set getPlatformsForReceivablesId() {
		return this.platformsForReceivablesId;
	}

	public void setPlatformsForReceivablesId(Set platformsForReceivablesId) {
		this.platformsForReceivablesId = platformsForReceivablesId;
	}

	public Set getDelivers() {
		return this.delivers;
	}

	public void setDelivers(Set delivers) {
		this.delivers = delivers;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

	public Set getCommentsForObjectId() {
		return this.commentsForObjectId;
	}

	public void setCommentsForObjectId(Set commentsForObjectId) {
		this.commentsForObjectId = commentsForObjectId;
	}

	public Set getPlatformsForPaymentId() {
		return this.platformsForPaymentId;
	}

	public void setPlatformsForPaymentId(Set platformsForPaymentId) {
		this.platformsForPaymentId = platformsForPaymentId;
	}

	public Set getPunishmentsForHandleId_1() {
		return this.punishmentsForHandleId_1;
	}

	public void setPunishmentsForHandleId_1(Set punishmentsForHandleId_1) {
		this.punishmentsForHandleId_1 = punishmentsForHandleId_1;
	}

	public Set getOrders_1() {
		return this.orders_1;
	}

	public void setOrders_1(Set orders_1) {
		this.orders_1 = orders_1;
	}

	public Set getReports() {
		return this.reports;
	}

	public void setReports(Set reports) {
		this.reports = reports;
	}

	public Set getTradinghistories() {
		return this.tradinghistories;
	}

	public void setTradinghistories(Set tradinghistories) {
		this.tradinghistories = tradinghistories;
	}

	public Set getCommentsForUsersId() {
		return this.commentsForUsersId;
	}

	public void setCommentsForUsersId(Set commentsForUsersId) {
		this.commentsForUsersId = commentsForUsersId;
	}

	public Set getReports_1() {
		return this.reports_1;
	}

	public void setReports_1(Set reports_1) {
		this.reports_1 = reports_1;
	}

	public Set getCommodities_1() {
		return this.commodities_1;
	}

	public void setCommodities_1(Set commodities_1) {
		this.commodities_1 = commodities_1;
	}

	public Set getUsersamounts() {
		return this.usersamounts;
	}

	public void setUsersamounts(Set usersamounts) {
		this.usersamounts = usersamounts;
	}

	public Set getDelivers_1() {
		return this.delivers_1;
	}

	public void setDelivers_1(Set delivers_1) {
		this.delivers_1 = delivers_1;
	}

	public Set getPlatformsForReceivablesId_1() {
		return this.platformsForReceivablesId_1;
	}

	public void setPlatformsForReceivablesId_1(Set platformsForReceivablesId_1) {
		this.platformsForReceivablesId_1 = platformsForReceivablesId_1;
	}

	public Set getChanges_1() {
		return this.changes_1;
	}

	public void setChanges_1(Set changes_1) {
		this.changes_1 = changes_1;
	}

	public Set getPlatformsForPaymentId_1() {
		return this.platformsForPaymentId_1;
	}

	public void setPlatformsForPaymentId_1(Set platformsForPaymentId_1) {
		this.platformsForPaymentId_1 = platformsForPaymentId_1;
	}

	public Set getPunishmentsForUsersId_1() {
		return this.punishmentsForUsersId_1;
	}

	public void setPunishmentsForUsersId_1(Set punishmentsForUsersId_1) {
		this.punishmentsForUsersId_1 = punishmentsForUsersId_1;
	}

	public Set getUsersamounts_1() {
		return this.usersamounts_1;
	}

	public void setUsersamounts_1(Set usersamounts_1) {
		this.usersamounts_1 = usersamounts_1;
	}

	public Set getTradinghistories_1() {
		return this.tradinghistories_1;
	}

	public void setTradinghistories_1(Set tradinghistories_1) {
		this.tradinghistories_1 = tradinghistories_1;
	}

	public Set getCommentsForObjectId_1() {
		return this.commentsForObjectId_1;
	}

	public void setCommentsForObjectId_1(Set commentsForObjectId_1) {
		this.commentsForObjectId_1 = commentsForObjectId_1;
	}

	public Set getCommentsForUsersId_1() {
		return this.commentsForUsersId_1;
	}

	public void setCommentsForUsersId_1(Set commentsForUsersId_1) {
		this.commentsForUsersId_1 = commentsForUsersId_1;
	}

}