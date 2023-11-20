package com.example.ems.models;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "performance")
public class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	
	@Column(name = "task_two")
	private Integer taskTwo; // Number of finished tasks 2 months ago
	@Column(name = "in_time_two")
	private Integer inTimeTwo; // % of finished tasks in time 2 months

	@Column(name = "task_one")
	private Integer taskOne; // Number of finished tasks 2 months ago
	@Column(name = "in_time_one")
	private Integer inTimeOne; // % of finished tasks in time 2 months

	@Column(name = "task_now")
	private Integer taskNow; // Number of finished tasks 2 months ago
	@Column(name = "in_time_now")
	private Integer inTimeNow; // % of finished tasks in time 2 months

	@Column(name = "task_sum")
	private Integer taskSum; // Number of finished tasks 2 months ago
	@Column(name = "in_time_sum")
	private Integer inTimeSum; // % of finished tasks in time 2 months
	private Boolean raise; //Up for raise?
	public Performance() {}
	
	public Performance(Employee employee, Integer taskTwo, Integer inTimeTwo, Integer taskOne, Integer inTimeOne, Integer taskNow, Integer inTimeNow, Integer taskSum, Integer inTimeSum, Boolean raise) {
		this.employee = employee;
		this.taskTwo = taskTwo;
		this.inTimeTwo = inTimeTwo;
		this.taskOne = taskOne;
		this.inTimeOne = inTimeOne;
		this.taskNow = taskNow;
		this.inTimeNow = inTimeNow;
		this.taskSum = taskSum;
		this.inTimeSum = inTimeSum;
		this.raise = raise;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getTaskNow() {
		return taskNow;
	}
	public void setTaskNow(Integer taskNow) {
		this.taskNow = taskNow;
	}
	public Integer getTaskOne() {
		return taskOne;
	}
	public void setTaskOne(Integer taskOne) {
		this.taskOne = taskOne;
	}
	public Integer getTaskTwo() {
		return taskTwo;
	}
	public void setTaskTwo(Integer taskTwo) {
		this.taskTwo = taskTwo;
	}
	public Integer getInTimeNow() {
		return inTimeNow;
	}
	public void setInTimeNow(Integer inTime) {
		this.inTimeNow = inTime;
	}
	public Integer getInTimeOne() {
		return inTimeOne;
	}
	public void setInTimeOne(Integer inTime) {
		this.inTimeOne = inTime;
	}
	public Integer getInTimeTwo() {
		return inTimeTwo;
	}
	public void setInTimeTwo(Integer inTime) {
		this.inTimeTwo = inTime;
	}
	public Integer getTaskSum() {
		return taskSum;
	}
	public void setTaskSum(Integer taskSum) {
		this.taskSum = taskSum;
	}
	public Integer getInTimeSum() {
		return inTimeSum;
	}
	public void setInTimeSum(Integer inTime) {
		this.inTimeSum = inTime;
	}
	public Boolean getRaise() {
		return raise;
	}
	public void setRaise(Boolean raise) {
		this.raise = raise;
	}
	
}
