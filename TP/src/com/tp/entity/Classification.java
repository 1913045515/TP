package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Classification entity. @author MyEclipse Persistence Tools
 */

public class Classification implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String describle;
	private String path;
	private Set commodities = new HashSet(0);
	private Set commodities_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Classification() {
	}

	/** full constructor */
	public Classification(String name, String describle, String path,
			Set commodities, Set commodities_1) {
		this.name = name;
		this.describle = describle;
		this.path = path;
		this.commodities = commodities;
		this.commodities_1 = commodities_1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrible() {
		return this.describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set getCommodities() {
		return this.commodities;
	}

	public void setCommodities(Set commodities) {
		this.commodities = commodities;
	}

	public Set getCommodities_1() {
		return this.commodities_1;
	}

	public void setCommodities_1(Set commodities_1) {
		this.commodities_1 = commodities_1;
	}

}