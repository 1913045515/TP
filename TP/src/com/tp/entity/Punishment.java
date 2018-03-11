package com.tp.entity;

import java.util.Date;

/**
 * Punishment entity. @author MyEclipse Persistence Tools
 */

public class Punishment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByUsersId;
	private Commodity commodity;
	private Users usersByHandleId;
	private String information;
	private Date time;

	// Constructors

	/** default constructor */
	public Punishment() {
	}

	/** full constructor */
	public Punishment(Users usersByUsersId, Commodity commodity,
			Users usersByHandleId, String information, Date time) {
		this.usersByUsersId = usersByUsersId;
		this.commodity = commodity;
		this.usersByHandleId = usersByHandleId;
		this.information = information;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByUsersId() {
		return this.usersByUsersId;
	}

	public void setUsersByUsersId(Users usersByUsersId) {
		this.usersByUsersId = usersByUsersId;
	}

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Users getUsersByHandleId() {
		return this.usersByHandleId;
	}

	public void setUsersByHandleId(Users usersByHandleId) {
		this.usersByHandleId = usersByHandleId;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}