package com.oseevol.service.impl;

import static com.oseevol.constants.DefaultMessages.ACTORS_NOT_FOUND;
import static com.oseevol.constants.DefaultMessages.ACTOR_NOT_FOUND;
import static com.oseevol.constants.DefaultMessages.BAD_REQUEST;
import static com.oseevol.constants.DefaultMessages.GENRE_NAME_EXISTS;
import static com.oseevol.constants.DefaultMessages.GENRE_NOT_FOUND;
import static com.oseevol.constants.DefaultMessages.INVALID_ID;
import static com.oseevol.constants.DefaultMessages.MOVIES_NOT_FOUND;
import static com.oseevol.constants.DefaultMessages.MOVIE_EXISTS;
import static com.oseevol.constants.DefaultMessages.OBJ_NULL_REF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.oseevol.controller.exception.BadRequestException;
import com.oseevol.controller.exception.ResourceAlreadyExistsException;
import com.oseevol.controller.exception.ResourceNotFoundException;
import com.oseevol.data.ActorDTO;
import com.oseevol.data.ActorUpdateDTO;
import com.oseevol.data.GenreDTO;
import com.oseevol.data.GenreUpdateDTO;
import com.oseevol.data.MovieActorCharacterDTO;
import com.oseevol.data.MovieActorCharacterDTO.CharacterActor;
import com.oseevol.data.MovieCharacterDTO;
import com.oseevol.data.MovieDTO;
import com.oseevol.data.MovieUpdateDTO;
import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;
import com.oseevol.data.entity.MovieCharacter;
import com.oseevol.repository.ActorRepository;
import com.oseevol.repository.GenreRepository;
import com.oseevol.repository.MovieCharacterRepository;
import com.oseevol.repository.MovieRepository;
import com.oseevol.repository.specs.ActorSpecification;
import com.oseevol.repository.specs.AssemblyOption;
import com.oseevol.repository.specs.GenreSpecification;
import com.oseevol.repository.specs.MovieSpecification;
import com.oseevol.repository.specs.SearchCriteria;
import com.oseevol.repository.specs.SearchOperation;
import com.oseevol.repository.specs.SearchSpecification;
import com.oseevol.service.LibraryService;
import com.oseevol.util.ObjectCopy;

@Service
public class LibraryServiceImpl implements LibraryService {

	private static final String ERROR = "error";
	private static final String SEARCH_PARAM_INVALID = "Search parameter invalid";
	private static final String OBJECT_NOT_FOUND = "Object not found";
	private static final String CHARACTHERS_NOT_FOUND = "No characters found";

	private final MovieRepository movieRepository;
	private final ActorRepository actorRepository;
	private final GenreRepository genreRepository;
	private final MovieCharacterRepository movieCharacterRepository;

	private final ModelMapper modelMapper;

	private static Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

	public LibraryServiceImpl(MovieRepository movieRepository, ActorRepository actorRepository,
			GenreRepository genreRepository, ModelMapper modelMapper,
			MovieCharacterRepository movieCharacterRepository) {
		super();
		this.movieRepository = movieRepository;
		this.actorRepository = actorRepository;
		this.genreRepository = genreRepository;
		this.movieCharacterRepository = movieCharacterRepository;
		this.modelMapper = modelMapper;
	}

	private void validateId(Long id) {

		if (id <= 0) {
			logger.info(INVALID_ID);
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, INVALID_ID);
			throw new BadRequestException(BAD_REQUEST, details);
		}
	}

	private void objectNotNull(Object obj) {
		if (obj == null) {
			logger.error(OBJ_NULL_REF);
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, OBJ_NULL_REF);
			throw new BadRequestException(BAD_REQUEST, details);
		}
	}

	private SearchSpecification buildCriteria(Map<String, String> parameters, SearchSpecification specification) {

		parameters.forEach((k, v) -> {
			SearchCriteria criteria = new SearchCriteria(k, v, SearchOperation.CONTAINS);
			specification.addCriteria(criteria);
		});

		return specification;
	}

	/**
	 * @returns all movies
	 */
	@Override
	public List<Movie> getMovies() {

		return movieRepository.findAll();
	}

	/**
	 * @param search parameters
	 * @returns list of movies satisfied to search parameters
	 */
	@Override
	public List<Movie> getMovies(Map<String, String> parameters) {

		objectNotNull(parameters);

		MovieSpecification specs = new MovieSpecification();

		buildCriteria(parameters, specs);

		if (!specs.hasCriteria()) {
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, SEARCH_PARAM_INVALID);
			throw new BadRequestException(BAD_REQUEST, details);
		}

		final List<Movie> movies = movieRepository.findAll(specs.getFilter(AssemblyOption.OR));
		if (movies.isEmpty()) {
			throw new ResourceNotFoundException(MOVIES_NOT_FOUND);
		}
		return movies;
	}

	/**
	 * @param id id of the movie
	 * @return a single movie
	 */
	@Override
	public Movie getMovie(Long id) {
		validateId(id);

		final Movie movie = movieRepository.findById(id).orElse(null);

		if (movie == null) {
			throw new ResourceNotFoundException(MOVIES_NOT_FOUND);
		}
		return movie;
	}

	@Override
	public List<Movie> getMoviesForGenre(Long id) {

		validateId(id);

		final Genre genre = genreRepository.findById(id).orElse(null);

		if (genre == null) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}

		final List<Movie> movies = movieRepository.findByGenres(genre);

		if (movies.isEmpty()) {
			throw new ResourceNotFoundException(MOVIES_NOT_FOUND);
		}

		return movies;
	}

	@Override
	public void deleteMovie(long id) {

		Movie movie = getMovie(id);
		movieRepository.delete(movie);
	}

	@Override
	public Movie addMovie(MovieDTO model) {
		objectNotNull(model);

		Optional<Movie> optional = movieRepository.findByTitle(model.getTitle());

		optional.ifPresent(movie -> {
			if (movie.getTitle().equals(model.getTitle())) {
				throw new ResourceAlreadyExistsException(MOVIE_EXISTS);
			}
		});

		Movie movie = modelMapper.map(model, Movie.class);
		return movieRepository.save(movie);
	}

	@Override
	public void updateMovie(MovieUpdateDTO dto) {
		objectNotNull(dto);

		var optional = movieRepository.findById(dto.getId());

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException(MOVIES_NOT_FOUND);
		}

		var movie = optional.get();

		var copy = new ObjectCopy();

		copy.copyMethods(dto, movie);

		movieRepository.save(movie);
	}
	//////////////////////////////////////////////////////////////////////////

	/**
	 * @returns list of genres
	 */
	public List<Genre> getGenres() {
		List<Genre> genres = genreRepository.findAll();
		if (genres.isEmpty()) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}
		return genres;
	}

	/**
	 * @param id id of the genre
	 * @returns single genre
	 */
	@Override
	public Genre getGenre(long id) {

		validateId(id);

		Genre genre = genreRepository.findById(id).orElse(null);

		if (genre == null) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}
		return genre;
	}

	@Override
	public List<Genre> getGenres(Map<String, String> parameters) {
		objectNotNull(parameters);

		GenreSpecification specs = new GenreSpecification();

		buildCriteria(parameters, specs);

		if (!specs.hasCriteria()) {
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, SEARCH_PARAM_INVALID);
			throw new BadRequestException(BAD_REQUEST, details);
		}

		final List<Genre> genres = genreRepository.findAll(specs.getFilter(AssemblyOption.OR));

		if (genres.isEmpty()) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}

		return genres;
	}

	@Override
	public Genre getGenreByName(String name) {
		objectNotNull(name);
		return genreRepository.findByName(name).orElse(null);
	}

	@Override
	public Genre addGenre(GenreDTO dto) {
		objectNotNull(dto);

		var optional = genreRepository.findByName(dto.getName());

		optional.ifPresent(genre -> {
			if (genre.getName().equalsIgnoreCase(dto.getName())) {
				throw new ResourceAlreadyExistsException(GENRE_NAME_EXISTS);
			}
		});

		Genre genre = modelMapper.map(dto, Genre.class);

		return genreRepository.save(genre);
	}

	@Override
	public void updateGenre(GenreUpdateDTO dto) {
		objectNotNull(dto);

		var optional = genreRepository.findById(dto.getId());

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}

		var genre = optional.get();

		var copy = new ObjectCopy();

		copy.copyMethods(dto, genre);

		genreRepository.save(genre);
	}

	@Override
	public void deleteGenre(long id) {

		final Genre genre = getGenre(id);

		if (genre == null) {
			throw new ResourceNotFoundException(GENRE_NOT_FOUND);
		}
		genreRepository.deleteById(id);
	}
	/////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Actor> getActors(Map<String, String> parameters) {
		objectNotNull(parameters);

		ActorSpecification specs = new ActorSpecification();

		buildCriteria(parameters, specs);

		if (!specs.hasCriteria()) {
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, SEARCH_PARAM_INVALID);
			throw new BadRequestException(BAD_REQUEST, details);
		}
		final List<Actor> actors = actorRepository.findAll(specs.getFilter(AssemblyOption.OR));
		if (actors.isEmpty()) {
			throw new ResourceNotFoundException(ACTORS_NOT_FOUND);
		}
		return actors;

	}

	@Override
	public List<Actor> getActors() {
		final List<Actor> actors = actorRepository.findAll();

		if (actors.isEmpty()) {
			throw new ResourceNotFoundException(ACTORS_NOT_FOUND);
		}

		return actors;
	}

	@Override
	public Actor getActor(Long id) {

		validateId(id);

		final Actor actor = actorRepository.findById(id).orElse(null);

		if (actor == null) {
			logger.error(OBJECT_NOT_FOUND);
			Map<String, String> details = new HashMap<>();
			details.put(ERROR, INVALID_ID);
			throw new ResourceNotFoundException(ACTOR_NOT_FOUND, details);
		}

		return actor;
	}

	@Override
	public List<Actor> getActorsForMovie(Long id) {
		Movie movie = getMovie(id);
		List<Actor> actors = actorRepository.findByMovies(movie);
		if (actors.isEmpty()) {
			throw new ResourceNotFoundException(ACTORS_NOT_FOUND);
		}
		return actors;
	}

	@Override
	public Actor addActor(ActorDTO model) {
		objectNotNull(model);

		Optional<Actor> optional = actorRepository.findByFirstNameAndLastName(model.getFirstName(),
				model.getLastName());
		optional.ifPresent(actor -> {
			throw new ResourceAlreadyExistsException(MOVIE_EXISTS);
		});

		Actor actor = modelMapper.map(model, Actor.class);
		return actorRepository.save(actor);
	}

	@Override
	public void updateActor(ActorUpdateDTO dto) {
		objectNotNull(dto);

		var optional = actorRepository.findById(dto.getId());

		if (optional.isEmpty()) {
			throw new ResourceNotFoundException(ACTORS_NOT_FOUND);
		}

		var actor = optional.get();

		var copy = new ObjectCopy();

		copy.copyMethods(dto, actor);

		actorRepository.save(actor);
	}

	@Override
	public MovieActorCharacterDTO getMovieCharacters(Long id) {
		validateId(id);

		Movie movie = getMovie(id);

		Optional<List<MovieCharacter>> optional = movieCharacterRepository.findCharactersByMovieId(movie.getId());

		if (optional.isEmpty())
			throw new ResourceNotFoundException(CHARACTHERS_NOT_FOUND);

		List<MovieCharacter> movieCharacters = optional.get();

		MovieCharacter character = movieCharacters.get(0);

		long movieId = character.getMovieId();

		Movie dbMovie = getMovie(movieId);

		MovieActorCharacterDTO movieCharactersDTO = new MovieActorCharacterDTO();

		movieCharactersDTO.setMovie(dbMovie);

		movieCharacters.forEach(mc -> {
			var actor = getActor(mc.getActorId());
			CharacterActor characterActor = new CharacterActor();
			characterActor.actor = actor;
			characterActor.character = mc.getCharacterName();
			movieCharactersDTO.getCharacters().add(characterActor);
		});

		return movieCharactersDTO;
	}

	@Override
	public MovieCharacter addCharacter(MovieCharacterDTO model) {
		Movie movie = getMovie(model.getMovie().getId());
		Actor actor = getActor(model.getActor().getId());

		MovieCharacter character = new MovieCharacter();

		character.setCharacterName(model.getCharacterName());
		character.setActorId(actor.getId());
		character.setMovieId(movie.getId());

		return movieCharacterRepository.save(character);
	}

}
