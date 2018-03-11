package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Promotion entity. @author MyEclipse Persistence Tools
 */

public class Promotion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String content;
	private String path;
	private Set promotioncommodities = new HashSet(0);
	private Set promotioncommodities_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Promotion() {
	}

	/** full constructor */
	public Promotion(String name, String content, String path,
			Set promotioncommodities, Set promotioncommodities_1) {
		this.name = name;
		this.content = content;
		this.path = path;
		this.promotioncommodities = promotioncommodities;
		this.promotioncommodities_1 = promotioncommodities_1;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set getPromotioncommodities() {
		return this.promotioncommodities;
	}

	public void setPromotioncommodities(Set promotioncommodities) {
		this.promotioncommodities = promotioncommodities;
	}

	public Set getPromotioncommodities_1() {
		return this.promotioncommodities_1;
	}

	public void setPromotioncommodities_1(Set promotioncommodities_1) {
		this.promotioncommodities_1 = promotioncommodities_1;
	}

}