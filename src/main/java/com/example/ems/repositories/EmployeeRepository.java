package com.example.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ems.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
}
