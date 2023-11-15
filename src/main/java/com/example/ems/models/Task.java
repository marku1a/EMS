package com.example.ems.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String taskName;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name = "due_date")
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dueDate;
	@ManyToOne
    @JoinColumn(name = "assignee_id")
	private Employee assignee;
	
	
	public Task() {};
	
	public Task(String taskName, Status status, LocalDate dueDate, Employee assignee) {
		this.taskName = taskName;
		this.status = status;
		this.dueDate = dueDate;
		this.assignee = assignee;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Employee getAssignee() {
        return assignee;
    }
    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }
	
}
