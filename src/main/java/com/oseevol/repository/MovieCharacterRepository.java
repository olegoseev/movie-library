package com.oseevol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oseevol.data.entity.MovieCharacter;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
	Optional<List<MovieCharacter>> findCharactersByMovieId(long id);
}
