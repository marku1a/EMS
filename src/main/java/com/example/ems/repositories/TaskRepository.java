package com.example.ems.repositories;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ems.models.Employee;
import com.example.ems.models.Status;
import com.example.ems.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	public Task findByTaskName(String taskName);

	public List<Task> findByAssigneeAndStatus(Employee employee, Status completed);
	
	


}
