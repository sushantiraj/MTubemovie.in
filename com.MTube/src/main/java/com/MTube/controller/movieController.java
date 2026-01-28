package com.MTube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.MTube.entity.Movie;
import com.MTube.services.movieService;

@Controller
public class movieController {
	@Autowired
	movieService mserv;
	
	@PostMapping("addmovie")
	public String addMovie(@ModelAttribute Movie mov) {
		mserv.addMovie(mov);
		return "addmoviesuccess";
	}
	
	@GetMapping("viewmovie")
	public String viewMovie(Model model) {
		List<Movie> movList=mserv.viewMovie();
		model.addAttribute("movie", movList);
		return "viewmovie";
	}
	
	@GetMapping("viewmovieuser")
	public String viewMovieUser(Model model) {
		List<Movie> movList=mserv.viewMovie();
		model.addAttribute("movie", movList);
		return "viewmovieuser";
	}
}
