package com.oseevol.controller;

import static com.oseevol.controller.RouterEndpoint.CREATE;
import static com.oseevol.controller.RouterEndpoint.GENRES_ROOT;
import static com.oseevol.controller.RouterEndpoint.MOVIE_GENRE;
import static com.oseevol.controller.RouterEndpoint.RESOURCE_ID;
import static com.oseevol.controller.RouterEndpoint.SEARCH;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.data.GenreDTO;
import com.oseevol.data.entity.Genre;
import com.oseevol.service.LibraryService;
import com.oseevol.util.ResponseWrapper;

@RestController
@RequestMapping(GENRES_ROOT)
public class GenreController {

	private static final String SLASH = "/";
	private final LibraryService libraryService;

	public GenreController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping
	public ResponseEntity<Object> getGenres() {
		return ResponseWrapper.ok(libraryService.getGenres());
	}

	@GetMapping(RESOURCE_ID)
	public ResponseEntity<Object> getGenre(@PathVariable(name = "id") long id) {
		return ResponseWrapper.ok(libraryService.getGenre(id));
	}

	@GetMapping(SEARCH)
	public ResponseEntity<Object> findGenres(@RequestParam Map<String, String> parameters) {
		return ResponseWrapper.ok(libraryService.getGenres(parameters));
	}

	@GetMapping(MOVIE_GENRE)
	public ResponseEntity<Object> getMoviesForGenre(@PathVariable(name = "id") long id) {
		return ResponseWrapper.ok(libraryService.getMoviesForGenre(id));
	}

	@PostMapping(CREATE)
	public ResponseEntity<Object> addGenre(@RequestBody GenreDTO genre, HttpServletRequest request) {

		Genre dbGenre = libraryService.addGenre(genre);
		var path = request.getRequestURI().replace(CREATE, SLASH + Long.toString(dbGenre.getId()));
		return ResponseWrapper.created(path);
	}

	@DeleteMapping(RESOURCE_ID)
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
		libraryService.deleteGenre(id);
		return ResponseWrapper.noContent();
	}
}
