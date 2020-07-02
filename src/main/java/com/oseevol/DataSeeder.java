package com.oseevol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;
import com.oseevol.repository.ActorRepository;
import com.oseevol.repository.GenreRepository;
import com.oseevol.repository.MovieRepository;


@Component
public class DataSeeder implements CommandLineRunner {

	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public void run(String... args) throws Exception {

		boolean add = false;
		
		if(add == true) {
			addGenres();
			addActors();
			
			addMoana();
			addInception();
			addJumanji();
		}
	}
	
	private void addJumanji() {
		
		Movie movie = new Movie();
		
		movie.setTitle("Jumanji: Welcome to the Jungle");
		movie.setDescription("Four teenagers are sucked into a magical video game, and the only way they can escape is to work together to finish the game.");
		movie.setInTheater(false);
		movie.setReleaseDate(LocalDate.of(2017, 12, 20));
		movie.setTrailer("2QKg5SZ_35I");
		movie.setPoster("https://m.media-amazon.com/images/M/MV5BODQ0NDhjYWItYTMxZi00NTk2LWIzNDEtOWZiYWYxZjc2MTgxXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SY1000_CR0,0,674,1000_AL_.jpg");
		
		Movie saved = movieRepository.save(movie);
		
		List<Movie> movies = new ArrayList<>();
		movies.add(saved);
		
		Genre comedy = genreRepository.findByName("Comedy").orElse(new Genre("Comedy"));
		saved.getGenres().add(comedy);
		comedy.setMovies(movies);
		
		Genre adventure = genreRepository.findByName("Adventure").orElse(new Genre("Adventure"));
		saved.getGenres().add(adventure);
		adventure.setMovies(movies);
		
		Genre animation = genreRepository.findByName("Action").orElse(new Genre("Action"));
		saved.getGenres().add(animation);
		animation.setMovies(movies);
		
		Actor rock = actorRepository.findByFirstNameAndLastName("Dwayne", "Johnson").orElse(null);
		if(rock != null) {
			saved.getActors().add(rock);
			rock.setMovies(movies);
		}
		
		Actor jack = actorRepository.findByFirstNameAndLastName("Jack", "Black").orElse(null);
		if(jack != null) {
			saved.getActors().add(jack);
			jack.setMovies(movies);
		}
		
		movieRepository.save(saved);
	}

	private void addInception() {
		Movie movie = new Movie();
		
		movie.setTitle("Inception");
		movie.setDescription("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.");
		movie.setInTheater(false);
		movie.setReleaseDate(LocalDate.of(2010, 7, 16));
		movie.setTrailer("YoHD9XEInc0");
		movie.setPoster("https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SY1000_CR0,0,675,1000_AL_.jpg");
		
		Movie saved = movieRepository.save(movie);
		
		List<Movie> movies = new ArrayList<>();
		movies.add(saved);
		
		
		Genre comedy = genreRepository.findByName("Comedy").orElse(new Genre("Comedy"));
		saved.getGenres().add(comedy);
		comedy.setMovies(movies);
		
		Genre adventure = genreRepository.findByName("Adventure").orElse(new Genre("Adventure"));
		saved.getGenres().add(adventure);
		adventure.setMovies(movies);
		
		Genre animation = genreRepository.findByName("Animation").orElse(new Genre("Animation"));
		saved.getGenres().add(animation);
		animation.setMovies(movies);
		
		Actor leo = actorRepository.findByFirstNameAndLastName("Leonardo", "DiCaprio").orElse(null);
		if(leo != null) {
			saved.getActors().add(leo);
			leo.setMovies(movies);
		}
		
		Actor page = actorRepository.findByFirstNameAndLastName("Ellen", "Page").orElse(null);
		if(page != null) {
			saved.getActors().add(page);
			page.setMovies(movies);
		}
		
		movieRepository.save(saved);
	}

	private void addMoana()
	{
		Movie movie = new Movie();
		
		movie.setTitle("Moana");
		movie.setDescription("In Ancient Polynesia, when a terrible curse incurred by the Demigod Maui reaches Moana's island, she answers the Ocean's call to seek out the Demigod to set things right.");
		movie.setInTheater(false);
		movie.setReleaseDate(LocalDate.of(2016, 11, 23));
		movie.setTrailer("LKFuXETZUsI&t=1s");
		movie.setPoster("https://m.media-amazon.com/images/M/MV5BMjI4MzU5NTExNF5BMl5BanBnXkFtZTgwNzY1MTEwMDI@._V1_SY1000_CR0,0,674,1000_AL_.jpg");
		
		Movie saved = movieRepository.save(movie);
		
		List<Movie> movies = new ArrayList<>();
		movies.add(saved);
		
		
		Genre comedy = genreRepository.findByName("Comedy").orElse(new Genre("Comedy"));
		saved.getGenres().add(comedy);
		comedy.setMovies(movies);
		
		Genre adventure = genreRepository.findByName("Adventure").orElse(new Genre("Adventure"));
		saved.getGenres().add(adventure);
		adventure.setMovies(movies);
		
		Genre animation = genreRepository.findByName("Animation").orElse(new Genre("Animation"));
		saved.getGenres().add(animation);
		animation.setMovies(movies);
		
		Actor crav = actorRepository.findByFirstNameAndLastName("Auli'i", "Cravalho").orElse(null);
		if(crav != null) {
			saved.getActors().add(crav);
			crav.setMovies(movies);
		}
		
		Actor rock = actorRepository.findByFirstNameAndLastName("Dwayne", "Johnson").orElse(null);
		if(rock != null) {
			saved.getActors().add(rock);
			rock.setMovies(movies);
		}
		
		movieRepository.save(saved);
	}
	
	private void addActors() {
		List<Actor> actors = new ArrayList<>();
		
		actors.add(new Actor("Dwayne", "Johnson", "here is some bio", 
				"https://m.media-amazon.com/images/M/MV5BMTkyNDQ3NzAxM15BMl5BanBnXkFtZTgwODIwMTQ0NTE@._V1_SY1000_CR0,0,665,1000_AL_.jpg",
				LocalDate.of(1972, 2, 05)));
		actors.add(new Actor("Auli'i", "Cravalho", "here is some bio",
				"https://m.media-amazon.com/images/M/MV5BNjIxZDI2MTctZTNhOC00NjdlLWI5ZTEtZjZhMmNkNGViZjczXkEyXkFqcGdeQXVyMTE5ODYzODk@._V1_.jpg",
				LocalDate.of(2000, 11, 22)));
		actors.add(new Actor("Leonardo", "DiCaprio", "here is some bio",
				"https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_.jpg",
				LocalDate.of(1974, 11, 11)));
		actors.add(new Actor("Ellen", "Page", "here is some bio",
				"https://m.media-amazon.com/images/M/MV5BMTU3MzM3MDYzMV5BMl5BanBnXkFtZTcwNzk1Mzc3NA@@._V1_SY1000_CR0,0,668,1000_AL_.jpg",
				LocalDate.of(1987, 2, 21)));
		actors.add(new Actor("Jack", "Black", "here is some bio",
				"https://m.media-amazon.com/images/M/MV5BYTFiMWNlNmItMDRiYy00NzA4LWE5YjItZmViNWQ2NzhhOWZlXkEyXkFqcGdeQXVyMTA2Mjc5ODMy._V1_.jpg",
				LocalDate.of(1969, 9, 28)));
		
		actorRepository.saveAll(actors);
	}

	private void addGenres()
	{
		List<Genre> genres = new ArrayList<>();
		genres.add(new Genre("Comedy"));
		genres.add(new Genre("Action"));
		genres.add(new Genre("Adventure"));
		genres.add(new Genre("Mistery"));
		genres.add(new Genre("Drama"));
		genres.add(new Genre("Fantasy"));
		genres.add(new Genre("Horror"));
		genres.add(new Genre("Si-Fi"));
		genres.add(new Genre("Animation"));
		genreRepository.saveAll(genres);
	}

}
