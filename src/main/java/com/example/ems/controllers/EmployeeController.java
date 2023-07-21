package com.example.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;




import com.example.ems.models.Employee;
import com.example.ems.models.Task;
import com.example.ems.services.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//Shows all employees
	@GetMapping("/employees")
	public String showEmployees(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "employees";
	}
	//Shows form for new employee
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	//Creates and saves new employee to database
	@PostMapping("/createNewEmployee")
	public String createNewEmployee(@ModelAttribute("employee") Employee employee) {
	    employeeService.createNewEmployee(employee);
	    return "redirect:/employees";
	}
	//Update employee
	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("employee") Employee employee) {
	    employeeService.updateEmployee(employee);
	    return "redirect:/employees";
	}
	//Shows form for updating employee
	@GetMapping("/showUpdateEmployeeForm/{id}")
	public String showUpdateEmployeeForm(@PathVariable(value = "id") Integer id, Model model) {
		Employee employee = employeeService.findEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	//Delete employee
	@GetMapping("/deleteEmployee/{id}")
	 public String deleteEmployee(@PathVariable(value = "id") Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
