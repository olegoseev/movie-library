package com.oseevol.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void shouldReturnThreeMovies() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/api/movies", String.class)).contains("Moana", "Inception","Jumanji: Welcome to the Jungle");
	}
}
