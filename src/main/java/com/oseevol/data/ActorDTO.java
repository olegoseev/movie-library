package com.oseevol.data;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ActorDTO {
	@NotEmpty(message = "First name is required")
	private String firstName;
	@NotEmpty(message = "Last name is required")
	private String lastName;
	@NotEmpty(message = "Bio is required")
	private String bio;
	@NotEmpty(message = "Picture is required")
	private String picture;
	@NotNull(message = "Date of birth is required")
	private LocalDate dateOfBirth;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
