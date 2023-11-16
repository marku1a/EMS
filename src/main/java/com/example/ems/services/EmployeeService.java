package com.example.ems.services;

import java.util.List;
import java.util.Optional;

import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	public Employee findEmployeeById(Integer id) {
		Optional<Employee> opt = empRepo.findById(id);
		return opt.orElse(null);
	}
	public Employee createNewEmployee(Employee employee) {
		return empRepo.save(employee);
	}
	public Employee updateEmployee(Employee employee) {
		employee.getId();
		return empRepo.save(employee);
	}
	public boolean deleteEmployee(Integer id) {
	    if (empRepo.existsById(id)) {
	        empRepo.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}
	
	
}
