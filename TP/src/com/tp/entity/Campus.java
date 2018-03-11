package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Campus entity. @author MyEclipse Persistence Tools
 */

public class Campus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String province;
	private String university;
	private Integer state;
	private Set userses = new HashSet(0);
	private Set userses_1 = new HashSet(0);
	private Set commodities = new HashSet(0);
	private Set commodities_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Campus() {
	}

	/** full constructor */
	public Campus(String province, String university, Integer state,
			Set userses, Set userses_1, Set commodities, Set commodities_1) {
		this.province = province;
		this.university = university;
		this.state = state;
		this.userses = userses;
		this.userses_1 = userses_1;
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

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getUserses_1() {
		return this.userses_1;
	}

	public void setUserses_1(Set userses_1) {
		this.userses_1 = userses_1;
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