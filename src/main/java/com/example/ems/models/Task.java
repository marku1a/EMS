package com.example.ems.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;


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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;
    @Column(name = "compl_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate complDate;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Employee assignee;


    public Task() {
    }

    public Task(String taskName, Status status, LocalDate dueDate, LocalDate complDate, Employee assignee) {
        this.taskName = taskName;
        this.status = status;
        this.dueDate = dueDate;
        this.assignee = assignee;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
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

    public LocalDate getComplDate() {
        return complDate;
    }

    public void setComplDate(LocalDate complDate) {
        this.complDate = complDate;
    }


    public static class TaskBuilder {
        private String taskName;
        private Status status;
        private LocalDate dueDate;
        private LocalDate complDate;
        private Employee assignee;

        TaskBuilder() {
        }

        public TaskBuilder taskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public TaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        @JsonFormat(pattern = "dd-MM-yyyy")
        public TaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        @JsonFormat(pattern = "dd-MM-yyyy")
        public TaskBuilder complDate(LocalDate complDate) {
            this.complDate = complDate;
            return this;
        }

        public TaskBuilder assignee(Employee assignee) {
            this.assignee = assignee;
            return this;
        }

        public Task build() {
            return new Task(this.taskName, this.status, this.dueDate, this.complDate, this.assignee);
        }
    }
}
