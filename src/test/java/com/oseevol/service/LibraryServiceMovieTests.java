package com.oseevol.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;

@SpringBootTest
public class LibraryServiceMovieTests {

	@Autowired
	private LibraryService libraryService;
	
	private Map<String, String> parameters = new HashMap<>();
	
	private void clearParameters() {
		parameters.clear();
	}
	
	private void addParameter(String key, String value) {
		parameters.put(key, value);
	}
	
	@Test
	public void findMovieByTitle() {

		clearParameters();
		addParameter("title", "Inception");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleUpperCase() {
		clearParameters();
		addParameter("title", "Inception".toUpperCase());

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleLowerCase() {
		clearParameters();
		addParameter("title", "Inception".toLowerCase());

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleFirstPart() {
		clearParameters();
		addParameter("title", "Jumanji");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleLastPart() {

		clearParameters();
		addParameter("title", "the Jungle");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleMiddlePart() {

		clearParameters();
		addParameter("title", "Welcome");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findMovieByTitleAndDescription() {
	
		clearParameters();
		addParameter("title", "Inception");
		addParameter("description", "corporate secret");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	
	@Test
	public void findMovieByTitleAndIncorrectDescriptionField() {
	
		clearParameters();
		addParameter("title", "Jumanji");
		addParameter("desc-tion", "corporate secret");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
		Movie movie = result.get(0);
		
		assertTrue(movie.getTitle().contains("Jumanji"));
		
	}
	
	@Test
	public void findMovieByDescription() {
		clearParameters();
		addParameter("description", "Ancient Polynesia");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(1, result.size(), "Sould find only one movie");
	}

	@Test
	public void findThreeMoviesByDescription() {
		clearParameters();
		addParameter("description", "the");

		List<Movie> result = libraryService.getMovies(parameters);

		assertEquals(3, result.size(), "Sould find two movies");
	}
	
	@Test
	public void findTwoMoviesWithAnimationGenre() {
		
		Genre genre = libraryService.getGenreByName("animation");
		
		assertEquals(9, genre.getId(), "Should find genre with valid id");
		
		List<Movie> result = libraryService.getMoviesForGenre(genre.getId());
		
		assertEquals(2, result.size(), "Sould find two movies");
	}
}
