package com.MTube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.MTube.entity.Movie;
import com.MTube.entity.User;
import com.MTube.services.movieService;
import com.MTube.services.userServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class userController {
	
	@Autowired
	userServices userv;
	@Autowired
	movieService movserv;
	
	
	@PostMapping("register")
	public String addUser(@ModelAttribute User usr) {
		boolean status=userv.emailExits(usr.getEmail());
		if(status==true) {
			return "registerfail";
		}
		else {
			userv.addUser(usr);
			return "registersuccess";
		}
		
	}
	
	@PostMapping("login")
	public String validateUSer(@RequestParam String email,@RequestParam String password, HttpSession session) {
		
		boolean loginStatus=userv.checkUser(email, password);
		
		if(loginStatus==true) {
			session.setAttribute("email", email);
			
			if(email.equals("admin@gmail.com")) {
				return "adminhome";
			}
			else {
				return "home";
			}
		}
		else {
			return "loginfail";
		}
	}
	
	@GetMapping("viewUser")
	public String viewUser(Model model) {
		List<User> usrList=userv.viewUser();
		model.addAttribute("user",usrList);		
		return "viewuser";
		
	}
	
	@GetMapping("exploremovies")
	public String exploreMovie(Model model, HttpSession session) {
		
		String email=(String)session.getAttribute("email");
		User usr=userv.getUser(email);
		
		if(usr.isPremium()==true) {
			List<Movie> movList=movserv.viewMovie();
			model.addAttribute("movie", movList);
			
			return "viewmovieuser";
		}
		else {
			return "payment";
		}
	}

	@DeleteMapping("delete")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userv.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
	
	
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	

	@PostMapping("updateuserprofile")
	public String updateUser(User usr) {
		
		userv.updateUser(usr);
		return "updatesuccess";
		
	}
	
	
	        
}
