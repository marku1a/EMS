package com.example.ems.services;


import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ems.dto.UserRegistrationDto;
import com.example.ems.models.User;



public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);
	List<User> getPendingUsers();
	void approveUser(Integer id);
	void deleteUser(Integer id);
	
}
