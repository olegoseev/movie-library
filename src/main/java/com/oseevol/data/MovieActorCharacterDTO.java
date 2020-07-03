package com.oseevol.data;

import java.util.HashMap;
import java.util.Map;

import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Movie;

public class MovieActorCharacterDTO {
	
	private Movie movie;
	private Map<String, Actor> actorCharacters = new HashMap<>();
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Map<String, Actor> getActorCharacters() {
		return actorCharacters;
	}
	public void setActorCharacters(Map<String, Actor> actorCharacters) {
		this.actorCharacters = actorCharacters;
	}

}
