package com.example.ems.services;

import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.ems.dto.UserRegistrationDto;
import com.example.ems.models.User;
import com.example.ems.models.Role;
import com.example.ems.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	


	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getName(),
							 registrationDto.getSurname(),
							 registrationDto.getEmail(),
							 passEncoder.encode(registrationDto.getPassword()),					
							 Arrays.asList(new Role("ROLE_PENDING")));
		user.setApproved(false);
		return userRepo.save(user);
	}
	@Override
	public void approveUser(Integer id) {
		Optional<User> opt = userRepo.findById(id);
		opt.ifPresent(user -> {
			user.setApproved(true);
			user.getRoles().clear();
			user.getRoles().add(new Role("ROLE_USER"));
			userRepo.save(user);
		});
}	
	@Override
	public void deleteUser(Integer id) {
		Optional<User> opt = userRepo.findById(id);
		opt.ifPresent(user -> {
			userRepo.delete(user);
		});
	}
	@Override
	public List<User> getPendingUsers() {
	    return userRepo.findByApprovedFalse();
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username not found or invalid.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
			user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
}
