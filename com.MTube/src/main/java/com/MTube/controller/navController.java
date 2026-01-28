package com.MTube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class navController {
	
	@GetMapping("map-signup")
	public String mapSignup() {
		return "register";
	}
	
	@GetMapping("map-login")
	public String maplogin() {
		return "login";
	}
	
	@GetMapping("map-addmovie")
	public String mapaddmovie() {
		return "addmovie";
	}
	@GetMapping("map-home")
	public String maphome(){
		return "home";
	}
	
	@GetMapping("map-adminhome")
	public String adminHome() {
		return "adminhome";
	}
	@GetMapping("updateprofile")
	public String updateProfile() {
		return "updateprofile";
	}
	
}
