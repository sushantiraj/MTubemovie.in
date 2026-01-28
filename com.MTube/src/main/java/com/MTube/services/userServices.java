package com.MTube.services;

import java.util.List;

import com.MTube.entity.User;

public interface userServices {
	
	public String addUser(User usr);
	
	public boolean emailExits(String email);
	
	public boolean checkUser(String email,String password);
	
	public List<User> viewUser();
	
	 void deleteUserById(int userId);
	
	public User getUser(String email);

	public void updateUser(User user);

}
