package com.oseevol.repository.specs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oseevol.data.entity.Movie;

public class MovieSpecification extends BaseSpecification<Movie> {
	private static Logger logger = LoggerFactory.getLogger(MovieSpecification.class);
	
	public MovieSpecification(){
		super(logger);
	}
}

