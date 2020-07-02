package com.oseevol.data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MovieCharacterDTO {

	@NotNull(message = "Movie id required")
	@Positive(message = "Id should be gerater than 0")
	private long movieId;
	@NotNull(message = "Actor id required")
	@Positive(message = "Id should be gerater than 0")
	private long actorId;
	@NotNull(message = "Character id required")
	@Positive(message = "Id should be gerater than 0")
	private long characterId;

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

	public long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(long characterId) {
		this.characterId = characterId;
	}
}
