package com.example.ems.controllers;

import com.example.ems.services.EmployeeService;
import com.example.ems.services.TaskService;
import com.example.ems.models.Employee;
import com.example.ems.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private EmployeeService employeeService;
	
	//Show tasks
	@GetMapping("/tasks")
    public String showTasks(Model model) {
		model.addAttribute("listTasks", taskService.getAllTasks());
		return "tasks";
	}
	//Show form for new tasks
    @GetMapping("/showNewTaskForm")
	public String showNewTaskForm(Model model) {
    	Task task = new Task();
	    model.addAttribute("task", task);
	    model.addAttribute("employees", employeeService.getAllEmployees()); 
	    return "new_task";
	}
	//Create and save new task to database
	@PostMapping("/createNewTask")
	public String createNewTask(@ModelAttribute("task") Task task) {
	    Employee assignee = task.getAssignee(); // Get the selected employee from the task form
	    task.setAssignee(assignee); // Set the Employee object as the assignee
	    taskService.createNewTask(task);
	    return "redirect:/tasks";
	}
	//Show form for update task
	@GetMapping("/showUpdateTaskForm/{id}")
	public String showUpdateTaskForm(@PathVariable Integer id, Model model) {
		Task task = taskService.findTaskById(id);
		model.addAttribute("task", task);
	    model.addAttribute("employees", employeeService.getAllEmployees()); 
	    model.addAttribute("formattedDueDate", task.getDueDate().toString());
		return "update_task";
	}
	//Update task in database
	@PostMapping("/updateTask")
	public String updateTask(@ModelAttribute("task") Task task) {
		taskService.updateTask(task);
		return "redirect:/tasks";
	}
	//Delete task
	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
		return "redirect:/tasks";
	}
}
