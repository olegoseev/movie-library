package com.oseevol.data;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MovieDTO {
	@NotEmpty(message = "Movie title is required")
	private String title;
	@NotEmpty(message = "Movie description is required")
	private String description;
	@NotEmpty(message = "Movie trailer is required")
	private String trailer;
	@NotEmpty(message = "Movie poster is required")
	private String poster;
	@NotNull(message = "Movie release date is required")
	private LocalDate releaseDate;
	
	private boolean inTheater = false;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isInTheater() {
		return inTheater;
	}

	public void setInTheater(boolean inTheater) {
		this.inTheater = inTheater;
	}
}
