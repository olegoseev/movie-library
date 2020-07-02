package com.oseevol.repository.specs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oseevol.data.entity.Genre;

public class GenreSpecification extends BaseSpecification<Genre> {
	
	private static Logger logger = LoggerFactory.getLogger(GenreSpecification.class);
	public GenreSpecification() {
		super(logger);
	}

}
