package com.MTube.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.MTube.entity.User;

public interface userRepository extends JpaRepository<User, Integer>{
	
	//method to find user by using email
		public User findByEmail(String email);
		public String deleteByEmail(String email);

}
