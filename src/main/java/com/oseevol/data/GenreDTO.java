package com.oseevol.data;

import javax.validation.constraints.NotEmpty;

public class GenreDTO {

	@NotEmpty(message = "Genre name is required")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
