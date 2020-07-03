package com.oseevol.service;

import java.util.List;
import java.util.Map;

import com.oseevol.data.ActorDTO;
import com.oseevol.data.GenreDTO;
import com.oseevol.data.MovieActorCharacterDTO;
import com.oseevol.data.MovieCharacterDTO;
import com.oseevol.data.MovieDTO;
import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;
import com.oseevol.data.entity.MovieCharacter;

public interface LibraryService {

	// movies
	List<Movie> getMovies();

	List<Movie> getMovies(Map<String, String> parameters);

	Movie getMovie(Long id);

	List<Movie> getMoviesForGenre(Long id);

	void deleteMovie(long id);

	// genres
	List<Genre> getGenres();

	List<Genre> getGenres(Map<String, String> parameters);

	Genre getGenre(long id);

	Genre getGenreByName(String name);

	Genre addGenre(GenreDTO model);

	void deleteGenre(long id);

	// actors
	List<Actor> getActors();

	Actor getActor(Long id);

	List<Actor> getActors(Map<String, String> parameters);

	List<Actor> getActorsForMovie(Long id);

	Movie addMovie(MovieDTO model);

	Actor addActor(ActorDTO actor);

	MovieCharacter getCharacter(Long id);

	MovieActorCharacterDTO getMovieCharacters(Long id);

	MovieCharacter addCharacter(MovieCharacterDTO model);
}
