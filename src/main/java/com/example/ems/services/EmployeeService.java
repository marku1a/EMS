package com.example.ems.services;

import java.util.List;
import java.util.Optional;

import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

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
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.empRepo.findAll(pageable);
	}
	
}
