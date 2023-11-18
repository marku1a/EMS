package com.example.ems.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public static BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests(
				authorize -> authorize
						.requestMatchers("/registration").permitAll()
						.requestMatchers("/js/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/img/**").permitAll()
						.anyRequest().authenticated()
		).formLogin(
				form -> form
						.loginPage("/login")
						.defaultSuccessUrl("/tasks")
						.permitAll()
						
		).logout(
				logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll()
		);
		return http.build();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passEncoder());
	}
}
