package com.oseevol.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oseevol.data.entity.Genre;

@SpringBootTest
public class LibraryServiceGenreTests {

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
	public void findGenreById() {

		long id = 4;
		String name = "Mistery";

		Genre genre = libraryService.getGenre(id);

		assertEquals(id, genre.getId(), String.format("Sould find genre with id %d", id));
		assertEquals(name, genre.getName(), String.format("Should find genre %s", name));
	}

	@Test
	public void findGenres() {
		int num = 11;

		List<Genre> genres = libraryService.getGenres();

		assertEquals(num, genres.size(), String.format("Sould find %d genres", num));
	}

	@Test
	public void findGenreByFullName() {
		clearParameters();
		addParameter("name", "Animation");

		List<Genre> genres = libraryService.getGenres(parameters);
		assertEquals(1, genres.size(), "Sould find only one genre");
		assertEquals("Animation", genres.get(0).getName(), String.format("Should find genre %s", "Animation"));
	}

	@Test
	public void findGenreByPartialName() {
		clearParameters();
		addParameter("name", "adven");

		List<Genre> genres = libraryService.getGenres(parameters);
		assertEquals(1, genres.size(), "Sould find only one genre");
	}
}
