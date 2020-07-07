package com.oseevol.data;

import javax.validation.constraints.Positive;

public class ActorUpdateDTO {
	@Positive
	private long id;
	private String bio;
	private String picture;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
