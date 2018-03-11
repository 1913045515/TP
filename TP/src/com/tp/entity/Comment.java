package com.tp.entity;

import java.util.Date;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByUsersId;
	private Commodity commodity;
	private Users usersByObjectId;
	private String content;
	private Date time;

	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** full constructor */
	public Comment(Users usersByUsersId, Commodity commodity,
			Users usersByObjectId, String content, Date time) {
		this.usersByUsersId = usersByUsersId;
		this.commodity = commodity;
		this.usersByObjectId = usersByObjectId;
		this.content = content;
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

	public Users getUsersByObjectId() {
		return this.usersByObjectId;
	}

	public void setUsersByObjectId(Users usersByObjectId) {
		this.usersByObjectId = usersByObjectId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}