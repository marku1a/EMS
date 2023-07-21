package com.example.ems.repositories;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ems.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	public Task findByTaskName(String taskName);
	
	


}
