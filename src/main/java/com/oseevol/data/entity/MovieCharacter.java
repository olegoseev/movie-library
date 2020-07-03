package com.oseevol.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movie_character")
public class MovieCharacter {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long id;
	
	@NotEmpty(message = "Character name is required")
	private String characterName;
	
	
	@Column(name = "movie_id")
	private long movieId;
	
	@Column(name = "actor_id")
	private long actorId;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

}
