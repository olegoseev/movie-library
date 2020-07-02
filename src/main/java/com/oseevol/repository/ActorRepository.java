package com.oseevol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oseevol.data.entity.Actor;
import com.oseevol.data.entity.Movie;

public interface ActorRepository extends JpaSpecificationExecutor<Actor>, JpaRepository<Actor, Long>{

	Optional<Actor> findByFirstNameAndLastName(String firstName, String lastName);
	List<Actor> findByMovies(Movie movie);
}
