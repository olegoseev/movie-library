package com.oseevol.repository.specs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oseevol.data.entity.Actor;

public class ActorSpecification extends BaseSpecification<Actor>{
	
	private static Logger logger = LoggerFactory.getLogger(ActorSpecification.class);
	
	public ActorSpecification() {
		super(logger);
	}
}
