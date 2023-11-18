package com.example.ems.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.ems.models.Employee;
import com.example.ems.models.Performance;
import com.example.ems.models.Status;
import com.example.ems.models.Task;


public class PerformanceCalculator {

	public static Performance calculator(Employee employee, List<Task> tasks, List<Employee> employees) {

		LocalDate now = LocalDate.now();
		LocalDate oneAgo = now.minusMonths(1);
		LocalDate twoAgo = now.minusMonths(2);
		
		//Filters tasks for set time periods
		List<Task> tasksNow = filterTasksForPeriod(tasks, now.withDayOfMonth(1), now); // First day of current month till today
		List<Task> tasksOneAgo = filterTasksForPeriod(tasks, oneAgo.withDayOfMonth(1), oneAgo.withDayOfMonth(oneAgo.lengthOfMonth())); //last month
		List<Task> tasksTwoAgo = filterTasksForPeriod(tasks, twoAgo.withDayOfMonth(1), twoAgo.withDayOfMonth(twoAgo.lengthOfMonth())); //2nd last
		
		Performance performance = new Performance();
		performance.setEmployee(employee);
		
		//returns numb of elements on list -> the number of tasks 
		Integer taskNow = tasksNow.size(); 
		Integer taskOne = tasksOneAgo.size();
		Integer taskTwo = tasksTwoAgo.size();
		
		Integer taskSum = taskTwo + taskOne + taskNow;
		performance.setTaskSum(taskSum);
		
		//Counts the number of tasks finished till due date
		Integer inTimeNow = inTimeCounter(tasksNow);
		Integer inTimeOne = inTimeCounter(tasksOneAgo);
		Integer inTimeTwo = inTimeCounter(tasksTwoAgo);
		
		//Calculation for inTimeSum
		Integer inTimeSum = 0;
		Integer[] inTimeArr = {inTimeNow, inTimeOne, inTimeTwo};
		Integer[] taskArr = {taskNow, taskOne, taskTwo};
		int count = 0;
		for (int i = 0; i < inTimeArr.length; i++) {
			if (taskArr[i] != null && taskArr[i] > 0) {
				inTimeSum += (inTimeArr[i] * 100) / taskArr[i];
				count++;
			}
		}
		if (count == 3) {
			performance.setInTimeSum(inTimeSum/3);
		} else if (count == 2) {
			performance.setInTimeSum(inTimeSum/2);
		} else {
			performance.setInTimeSum(inTimeSum);
		}
		
		
		
		performance.setTaskNow(taskNow);
		performance.setTaskOne(taskOne);
		performance.setTaskTwo(taskTwo);
		
		if (taskNow > 0) {
			Integer inTime = inTimeNow * 100 / taskNow;
			performance.setInTimeNow(inTime);	
		}
		if (taskOne > 0) {
			Integer inTime = inTimeOne * 100 / taskOne;
			performance.setInTimeOne(inTime);
		}
		if (taskTwo > 0) {
			Integer inTime = inTimeTwo * 100 / taskTwo;
			performance.setInTimeTwo(inTime);
		}
		
		double avgSalary = avgSalary(employees);
		int inTimeS = performance.getInTimeSum();
		upForRaise(employee, avgSalary, inTimeS, taskTwo, taskOne, performance);
		
		return performance;
}
	
	private static List<Task> filterTasksForPeriod(List<Task> tasks, LocalDate startDate, LocalDate endDate) {
		List<Task> filtered = new ArrayList<>();
		for (Task task : tasks) {
			LocalDate taskComplDate = task.getComplDate();
			if(!taskComplDate.isBefore(startDate) && !taskComplDate.isAfter(endDate)) {
				filtered.add(task);
			}
		}
		return filtered;
		
	}
	
	private static int inTimeCounter(List<Task> tasks) {
		int counter = 0;
		for (Task task : tasks) {
			if (task.getStatus() == Status.Completed && !task.getComplDate().isAfter(task.getDueDate())) {
				counter++;
			}
		}
		return counter;
	}
	private static double avgSalary(List<Employee> employees) {
		int counter = 0;
		double total = 0;
		for (Employee employee : employees) {
			if(employee.getSalary() != null) {
			total += employee.getSalary();
			counter++;
			}
		}
		if (counter > 0) {
		return total/counter;
		} else return 0.0;
	}
	public static void upForRaise(Employee employee, double avgSalary, int inTimeS, int taskTwo, int taskOne, Performance performance) {
		if(employee.getSalary() != null) {
		performance.setRaise((employee.getSalary() < avgSalary) && inTimeS > 90 && taskTwo > 0 && taskOne > 0);
		}
	}
	
	
  
    
	// Private constructor to prevent instantiation
    private PerformanceCalculator() {
        throw new UnsupportedOperationException("This is a utility class and should not be instantiated.");
    }
    
}
