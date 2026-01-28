package com.MTube.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MTube.entity.Movie;
import com.MTube.repository.movieRepository;

@Service
public class movieServiceImplements implements movieService{

	@Autowired
	movieRepository mRepo;
	
	
	@Override
	public String addMovie(Movie mov) {
		mRepo.save(mov);
		return "Movie object is created.";
	}
	
	@Override
	public List<Movie> viewMovie() {
		List<Movie> movList=mRepo.findAll();
		return movList;
	}
}
