package com.example.ems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ems.models.Employee;
import com.example.ems.models.Performance;
import com.example.ems.models.Task;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.repositories.PerformanceRepository;
import com.example.ems.util.PerformanceCalculator;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepo;
    
    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired 
    private TaskService taskService;

    @Transactional
    public void updatePerformance() {
        List<Employee> employees = employeeRepo.findAll();

        for (Employee employee : employees) {
            List<Task> completedTasks = taskService.getCompletedTasksByEmployee(employee);

            // Calculate performance and associate it with the employee
            Performance performance = PerformanceCalculator.calculator(employee, completedTasks);

            // Save or update performance
            saveOrUpdate(performance);
            
        	}
        }

    @Transactional(readOnly = true)
    public List<Performance> getAllPerformances() {
        return performanceRepo.findAll();
    }

    @Transactional
    public void saveOrUpdate(Performance performance) {
    	Optional<Performance> existingP = performanceRepo.findByEmployeeId(performance.getEmployee().getId());
    	if(existingP.isPresent()) {
    		Performance existing = existingP.get(); //if it exists update it 
    		existing.setTaskTwo(performance.getTaskTwo());
    		existing.setTaskOne(performance.getTaskOne());
    		existing.setTaskNow(performance.getTaskNow());
    		existing.setTaskSum(performance.getTaskSum());
    		existing.setInTimeTwo(performance.getInTimeTwo());
    		existing.setInTimeOne(performance.getInTimeOne());
    		existing.setInTimeNow(performance.getInTimeNow());
    		existing.setInTimeSum(performance.getInTimeSum());
    		performanceRepo.save(existing);
    	} else {
    		performanceRepo.save(performance); // Employee doesn't exist -> save new entry
    		
    	}
        
    }
}