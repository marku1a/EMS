package com.example.ems.initializers;
import com.example.ems.models.Role;



import com.example.ems.models.User;
import com.example.ems.repositories.UserRepository; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

// Initializing user with admin role for empty database - in this case if admin@ems.com doesn't exist

@Service
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
	private BCryptPasswordEncoder passEncoder;

    @Override
    public void run(String... args) throws Exception {
       
        if (userRepository.findByEmail("admin@ems.com") == null) {
        	
        	Role adminRole = new Role("ROLE_ADMIN");

            User adminUser = new User("Admin", "User", "admin@ems.com", passEncoder.encode("@dm!n"), Collections.singletonList(adminRole));
            userRepository.save(adminUser);
        }
    }
}