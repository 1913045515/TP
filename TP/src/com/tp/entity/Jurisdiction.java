package com.tp.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Jurisdiction entity. @author MyEclipse Persistence Tools
 */

public class Jurisdiction implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer flag;
	private String type;
	private String content;
	private Set userses = new HashSet(0);
	private Set userses_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Jurisdiction() {
	}

	/** full constructor */
	public Jurisdiction(Integer flag, String type, String content, Set userses,
			Set userses_1) {
		this.flag = flag;
		this.type = type;
		this.content = content;
		this.userses = userses;
		this.userses_1 = userses_1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

}