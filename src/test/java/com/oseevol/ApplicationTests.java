package com.oseevol;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.oseevol.controller.ActorController;
import com.oseevol.controller.GenreController;
import com.oseevol.controller.MovieController;
import com.oseevol.service.LibraryService;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private MovieController movieController;
	
	@Autowired
	private GenreController genreController;
	
	@Autowired
	private ActorController actorController;
	
	@Test
	void contextLoads() {
		assertThat(libraryService).isNotNull();
		assertThat(movieController).isNotNull();
		assertThat(genreController).isNotNull();
		assertThat(actorController).isNotNull();
	}

}
