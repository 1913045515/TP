package com.tp.entity;
public class JpushInfo {
	private String themes;
	private String content;
	private long timeToLive;
	public JpushInfo(){
		
	}
	public JpushInfo(String themes, String content, long timeToLive) {
		super();
		this.themes = themes;
		this.content = content;
		this.timeToLive = timeToLive;
	}
	public String getThemes() {
		return themes;
	}
	public void setThemes(String themes) {
		this.themes = themes;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
	
}
