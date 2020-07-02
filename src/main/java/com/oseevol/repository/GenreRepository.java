package com.oseevol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.oseevol.data.entity.Genre;

public interface GenreRepository extends JpaSpecificationExecutor<Genre>, JpaRepository<Genre, Long>{
	Optional<Genre> findByName(String name);
}
