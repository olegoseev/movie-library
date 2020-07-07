package com.oseevol.controller;

public final class RouterEndpoint {
	private RouterEndpoint() {}
	
	public static final String MOVIES_ROOT = "/movies";
	public static final String GENRES_ROOT = "/genres";
	public static final String RESOURCE_ID = "/{id}";
	public static final String SEARCH = "/search";
	public static final String UPDATE = "/update";
	public static final String CREATE = "/create";
	public static final String MOVIE_GENRE = "/{id}/movies";
	public static final String RESOURCE_NAME = "/{name}";
	public static final String ACTORS_ROOT = "/actors";
	public static final String ACTORS_MOVIE = "/{id}/actors";
	public static final String CHARACTERS_ROOT = "/characters";
	public static final String CHARACTERS_MOVIE = "/movie/{id}";
}
