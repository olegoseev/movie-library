package com.oseevol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oseevol.data.entity.Genre;
import com.oseevol.data.entity.Movie;


public interface MovieRepository extends JpaSpecificationExecutor<Movie>,  JpaRepository <Movie, Long>  {
	List<Movie> findByGenres(Genre genre);

	Optional<Movie> findByTitle(String title);
}
