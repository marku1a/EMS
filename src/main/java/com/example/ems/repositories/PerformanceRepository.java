package com.example.ems.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.models.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

	public Optional<Performance> findById(Integer performanceId);
	public Optional<Performance> findByEmployeeId(Integer employeeId);
}
