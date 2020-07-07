package com.oseevol.controller;

import static com.oseevol.constants.DefaultMessages.MOVIES_NOT_FOUND;
import static com.oseevol.controller.RouterEndpoint.ACTORS_MOVIE;
import static com.oseevol.controller.RouterEndpoint.CREATE;
import static com.oseevol.controller.RouterEndpoint.MOVIES_ROOT;
import static com.oseevol.controller.RouterEndpoint.RESOURCE_ID;
import static com.oseevol.controller.RouterEndpoint.SEARCH;
import static com.oseevol.controller.RouterEndpoint.UPDATE;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.controller.exception.ResourceNotFoundException;
import com.oseevol.data.MovieDTO;
import com.oseevol.data.MovieUpdateDTO;
import com.oseevol.data.entity.Movie;
import com.oseevol.service.LibraryService;
import com.oseevol.util.ResponseWrapper;

@RestController
@RequestMapping(MOVIES_ROOT)
public class MovieController {

	private static final String SLASH = "/";

	private final LibraryService libraryService;

	public MovieController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping(RESOURCE_ID)
	public ResponseEntity<Object> getMovie(@PathVariable(name = "id") Long id) {
		return ResponseWrapper.ok(libraryService.getMovie(id));
	}

	@GetMapping()
	public ResponseEntity<Object> getMovies() {

		final List<Movie> movies = libraryService.getMovies();

		if (movies.isEmpty()) {
			throw new ResourceNotFoundException(MOVIES_NOT_FOUND);
		}
		return ResponseWrapper.ok(movies);
	}

	@GetMapping(SEARCH)
	public ResponseEntity<Object> getMovies(@RequestParam Map<String, String> parameters) {
		return ResponseWrapper.ok(libraryService.getMovies(parameters));
	}

	@PostMapping(CREATE)
	public ResponseEntity<Object> createMovie(@Valid @RequestBody MovieDTO dto, HttpServletRequest request) {

		var movie = libraryService.addMovie(dto);
		var path = request.getRequestURI().replace(CREATE, SLASH + Long.toString(movie.getId()));
		return ResponseWrapper.created(path);
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<Object> updateMovie(@RequestBody MovieUpdateDTO dto){
		libraryService.updateMovie(dto);
		return ResponseWrapper.noContent();
	}

	@DeleteMapping(RESOURCE_ID)
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {

		libraryService.deleteMovie(id);
		return ResponseWrapper.noContent();
	}

	@GetMapping(ACTORS_MOVIE)
	public ResponseEntity<Object> getActorsForMovie(@PathVariable(name = "id") Long id) {
		return ResponseWrapper.ok(libraryService.getActorsForMovie(id));
	}
}
