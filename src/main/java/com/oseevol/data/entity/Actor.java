package com.oseevol.data.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actor")
public class Actor {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty(message = "First name is required")
	private String firstName;
	@NotEmpty(message = "Last name is required")
	private String lastName;
	@NotEmpty(message = "Bio is required")
	private String bio;
	@NotEmpty(message = "Picture is required")
	private String picture;
	
	@Column(name = "dob")
	@NotNull(message = "Date of birth is required")
	private LocalDate dateOfBirth;
	
	@JsonIgnore
	//@Transient
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="actors")
	private List<Movie> movies = new ArrayList<>();
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "actor_id", nullable=false, insertable=false)
	private List<MovieCharacter> characters = new ArrayList<>();
	
	public Actor() {}
	
	public Actor(String firstName, String lastName, String bio, String picture, LocalDate dateOfBirth)	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
		this.picture = picture;
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((movies == null) ? 0 : movies.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (movies == null) {
			if (other.movies != null)
				return false;
		} else if (!movies.equals(other.movies))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		return true;
	}
	
}
