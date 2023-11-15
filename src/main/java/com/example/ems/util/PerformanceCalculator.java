package com.example.ems.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.ems.models.Employee;
import com.example.ems.models.Performance;
import com.example.ems.models.Status;
import com.example.ems.models.Task;


public class PerformanceCalculator {

	public static Performance calculator(Employee employee, List<Task> tasks) {
		System.out.println("Calculating performance for employee: " + employee.getId());
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
		
		//Counts the number of tasks finished till due date
		Integer inTimeNow = inTimeCounter(tasksNow);
		Integer inTimeOne = inTimeCounter(tasksOneAgo);
		Integer inTimeTwo = inTimeCounter(tasksTwoAgo);
		
		performance.setTaskNow(taskNow);
		performance.setTaskOne(taskOne);
		performance.setTaskTwo(taskTwo);
		
		if (taskNow > 0) {
			Integer inTime = (inTimeNow / taskNow) * 100;
			performance.setInTimeNow(inTime);	
		}
		if (taskOne > 0) {
			Integer inTime = ( inTimeOne / taskOne) * 100;
			performance.setInTimeOne(inTime);
		}
		if (taskTwo > 0) {
			Integer inTime = ( inTimeTwo / taskTwo) * 100;
			performance.setInTimeTwo(inTime);
		}
		
		Integer taskSum = taskTwo + taskOne + taskNow;
		performance.setTaskSum(taskSum);
		
		Integer inTimeSum = inTimeTwo + inTimeOne + inTimeNow;
		performance.setInTimeSum(inTimeSum);
		
		
		return performance;
	}
	
	private static List<Task> filterTasksForPeriod(List<Task> tasks, LocalDate startDate, LocalDate endDate) {
		List<Task> filtered = new ArrayList<>();
		for (Task task : tasks) {
			LocalDate taskDueDate = task.getDueDate();
			if(!taskDueDate.isBefore(startDate) && !taskDueDate.isAfter(endDate)) {
				filtered.add(task);
			}
		}
		return filtered;
		
	}
	private static int inTimeCounter(List<Task> tasks) {
		int counter = 0;
		for (Task task : tasks) {
			if (task.getStatus() == Status.Completed) {
				counter++;
			}
		}
		return counter;
	}
	
  
    
	// Private constructor to prevent instantiation
    private PerformanceCalculator() {
        throw new UnsupportedOperationException("This is a utility class and should not be instantiated.");
    }
    
}
