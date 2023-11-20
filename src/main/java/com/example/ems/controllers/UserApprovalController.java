package com.example.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ems.models.User;
import com.example.ems.services.UserService;

@Controller
@RequestMapping
public class UserApprovalController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user_approval")
	public String showPendingUsers(Model model) {
		List<User> pendingUsers = userService.getPendingUsers();
		model.addAttribute("pendingUsers", pendingUsers);
		return "/user_approval";
	}
	@PostMapping("/approveUser/{id}")
	public String approveUser(@PathVariable(value = "id") Integer id) {
		userService.approveUser(id);
		return "redirect:/user_approval";
	}
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") Integer id) {
		userService.deleteUser(id);
		return "redirect:/user_approval";
	}
	
}
	

