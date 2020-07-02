package com.oseevol.data.entity;

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
	
	@NotEmpty(message = "Movie is required")
	private long movie_id;
	
	@NotEmpty(message = "Actor is required")
	private long actor_id;

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

	public long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}

	public long getActor_id() {
		return actor_id;
	}

	public void setActor_id(long actor_id) {
		this.actor_id = actor_id;
	}
}
