package com.MTube.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MTube.entity.User;
import com.MTube.repository.userRepository;
@Service
public class userServiceImplements implements userServices{

	@Autowired
	userRepository uRepo;
	
	
	@Override
	public String addUser(User usr) {
		uRepo.save(usr);
		return "User is addedd";
	}

	@Override
	public boolean emailExits(String email) {
		if (uRepo.findByEmail(email)==null) 
		{
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public boolean checkUser(String email, String password) {
		//checking email and getting user details 
		User usr=uRepo.findByEmail(email);
		//getting db password using email
		String db_password=usr.getPassword();
		
		//Checking whether user entered password and db_password is equals or not
		if(db_password.equals(password)) {
			//if same, returning true
			return true;
		}
		else {
			//if not same returning false
			return false;
		}
	}

	@Override
	public List<User> viewUser() {
		List<User>usrList=uRepo.findAll();
		return usrList;
	}

	

	@Override
	public User getUser(String email) {
		User user=uRepo.findByEmail(email);
		return user;
	}

	@Override
	public void updateUser(User user) {
		uRepo.save(user);		
	}
	
	@Override
    public void deleteUserById(int userId) {
        uRepo.deleteById(userId);
    }
}















