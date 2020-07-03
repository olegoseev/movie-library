package com.oseevol.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Movie;

public class MovieCharacterDTO {

	@NotEmpty(message = "Character name is required")
	private String characterName;
	@NotNull(message = "Movie is required")
	private Movie movie;
	@NotNull(message = "Actor is required")
	private Actor actor;
	
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
}
