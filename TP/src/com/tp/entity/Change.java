package com.tp.entity;

import java.util.Date;

/**
 * Change entity. @author MyEclipse Persistence Tools
 */

public class Change implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String table;
	private String information;
	private Date time;

	// Constructors

	/** default constructor */
	public Change() {
	}

	/** full constructor */
	public Change(Users users, String table, String information, Date time) {
		this.users = users;
		this.table = table;
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

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
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