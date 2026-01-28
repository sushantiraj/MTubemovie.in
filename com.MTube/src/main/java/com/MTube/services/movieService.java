package com.MTube.services;

import java.util.List;

import com.MTube.entity.Movie;

public interface movieService {
	
	public String addMovie(Movie mov);
	
	List<Movie> viewMovie();
	
}
