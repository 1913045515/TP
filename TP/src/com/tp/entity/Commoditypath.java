package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Commoditypath entity. @author MyEclipse Persistence Tools
 */

public class Commoditypath implements java.io.Serializable {

	// Fields

	private Integer id;
	private Commodity commodity;
	private String path;
	private Integer indexNumber;
	private Set commodities = new HashSet(0);
	private Set commodities_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Commoditypath() {
	}

	/** full constructor */
	public Commoditypath(Commodity commodity, String path, Integer indexNumber,
			Set commodities, Set commodities_1) {
		this.commodity = commodity;
		this.path = path;
		this.indexNumber = indexNumber;
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

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getIndexNumber() {
		return this.indexNumber;
	}

	public void setIndexNumber(Integer indexNumber) {
		this.indexNumber = indexNumber;
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