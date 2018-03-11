package com.tp.entity;

/**
 * Promotioncommodity entity. @author MyEclipse Persistence Tools
 */

public class Promotioncommodity implements java.io.Serializable {

	// Fields

	private Integer id;
	private Commodity commodity;
	private Promotion promotion;

	// Constructors

	/** default constructor */
	public Promotioncommodity() {
	}

	/** full constructor */
	public Promotioncommodity(Commodity commodity, Promotion promotion) {
		this.commodity = commodity;
		this.promotion = promotion;
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

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}