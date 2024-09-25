package com.example.ems.services;

import java.util.List;

import java.util.Optional;

import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.models.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {

	private final EmployeeRepository empRepo;

    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

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
		Optional<Employee> empl = empRepo.findById(employee.getId());
		if (empl.isPresent()) {
			Employee existingEmpl = empl.get();
			existingEmpl.setName(employee.getName());
			existingEmpl.setSurname(employee.getSurname());
			existingEmpl.setEmail(employee.getEmail());
			existingEmpl.setDepartment(employee.getDepartment());
			existingEmpl.setPhone(employee.getPhone());
			existingEmpl.setSalary(employee.getSalary());

			return empRepo.save(existingEmpl);
		} else {
			throw new EntityNotFoundException("Not found.");
		}
		}
	public boolean deleteEmployee(Integer employeeId) {
	    if (empRepo.existsById(employeeId)) {
	        empRepo.deleteById(employeeId);
	        return true;
	    } else {
	        return false;
	    }
	}



}
