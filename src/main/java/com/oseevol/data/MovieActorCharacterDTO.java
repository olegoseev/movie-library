package com.oseevol.data;

import java.util.ArrayList;
import java.util.List;

import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Movie;

public class MovieActorCharacterDTO {
	
	public static class CharacterActor {
		public String character;
		public Actor actor;
	}
	
	private Movie movie;

	private List<CharacterActor> characters = new ArrayList<>();
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public List<CharacterActor> getCharacters() {
		return characters;
	}
	public void setCharacters(List<CharacterActor> characters) {
		this.characters = characters;
	}
}
