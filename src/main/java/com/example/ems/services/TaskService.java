package com.example.ems.services;

import java.util.List;

import java.util.Optional;

import com.example.ems.models.*;
import com.example.ems.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TaskService {
	@Autowired	
	private TaskRepository taskRepo;
	
	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	public Task findTaskById(Integer id) {
		Optional<Task> opt = taskRepo.findById(id);
		return opt.orElse(null);
	}
	
	public Task createNewTask(Task task) {
		return taskRepo.save(task);
	}
	public Task updateTask(Task task) {
	
		return taskRepo.save(task);
	}
	public boolean deleteTask(Integer id) {
		if (taskRepo.existsById(id)) {
	        taskRepo.deleteById(id);
	        return true;
	    } else {
	        return false;
	    }
	}
}
