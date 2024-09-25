package com.example.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ems.services.PerformanceService;

@Controller
@RequestMapping
public class PerformanceController {


	private final PerformanceService performanceService;


    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping("/performance")
	public String showPerformance(Model model) {
		model.addAttribute("listPerformance", performanceService.getAllPerformances());
		return "performance";
	}
	@PostMapping("/updatePerformance")
    public String updatePerformance() {
        performanceService.updatePerformance();
        return "redirect:/performance";
    }
}
