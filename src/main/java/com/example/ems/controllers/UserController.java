package com.example.ems.controllers;


import com.example.ems.dto.UserRegistrationDto;
import com.example.ems.models.User;
import com.example.ems.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //User approval
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

    //User registration
    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }
    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto) {
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";
    }
}
