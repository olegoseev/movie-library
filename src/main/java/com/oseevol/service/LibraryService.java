package com.oseevol.service;

import java.util.List;
import java.util.Map;

import com.oseevol.data.ActorDTO;
import com.oseevol.data.ActorUpdateDTO;
import com.oseevol.data.GenreDTO;
import com.oseevol.data.GenreUpdateDTO;
import com.oseevol.data.MovieActorCharacterDTO;
import com.oseevol.data.MovieCharacterDTO;
import com.oseevol.data.MovieDTO;
import com.oseevol.data.MovieUpdateDTO;
import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;
import com.oseevol.data.entity.MovieCharacter;

public interface LibraryService {
	////////////////////////
	// movies
	List<Movie> getMovies();

	List<Movie> getMovies(Map<String, String> parameters);
	
	Movie addMovie(MovieDTO model);

	void updateMovie(MovieUpdateDTO dto);

	Movie getMovie(Long id);

	List<Movie> getMoviesForGenre(Long id);

	void deleteMovie(long id);
	
	//////////////////////////////
	// genres
	List<Genre> getGenres();

	List<Genre> getGenres(Map<String, String> parameters);

	Genre getGenre(long id);

	Genre getGenreByName(String name);

	Genre addGenre(GenreDTO dto);

	void updateGenre(GenreUpdateDTO dto);

	void deleteGenre(long id);
	///////////////////////////////
	// actors
	List<Actor> getActors();

	Actor getActor(Long id);

	List<Actor> getActors(Map<String, String> parameters);

	List<Actor> getActorsForMovie(Long id);


	Actor addActor(ActorDTO actor);

	void updateActor(ActorUpdateDTO dto);

	////////////////////////////
	// character
	MovieActorCharacterDTO getMovieCharacters(Long id);

	MovieCharacter addCharacter(MovieCharacterDTO model);



}
