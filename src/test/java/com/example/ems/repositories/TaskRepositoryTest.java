package com.example.ems.repositories;


import com.example.ems.models.Status;
import com.example.ems.models.Task;
import com.example.ems.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Task task, task2;
    private Employee assignee;

    @BeforeEach
    void init() {
        assignee = new Employee();
        assignee.setName("John Wick");
        employeeRepository.save(assignee); //Need to save assignee or we get transient ex
        task = Task.builder()
                .taskName("A task")
                .status(Status.valueOf("In_progress"))
                .dueDate(LocalDate.of(2024, 2, 12))
                .complDate(LocalDate.of(2024, 2, 10))
                .assignee(assignee)
                .build();
        task2 = Task.builder()
                .taskName("Also a task")
                .status(Status.valueOf("Completed"))
                .dueDate(LocalDate.of(2024, 2, 2))
                .complDate(LocalDate.of(2024, 2, 5))
                .assignee(assignee)
                .build();
    }
    @Test
    void findById_shouldReturnNotNull() {
        taskRepository.save(task);

        Optional<Task> taskCheck = taskRepository.findById(task.getId());

        assertThat(taskCheck).isNotNull();
    }
    @Test
    void findAll_shouldReturnAllTasks() {
        List<Task> taskList = List.of(task, task2);
        taskRepository.saveAll(taskList);
        List<Task> taskRepo = taskRepository.findAll();
        assertThat(taskRepo).isNotNull()
                .isEqualTo(taskList);
    }
    @Test
    void save_shouldReturnSavedEmployee() {
        Task saved = taskRepository.save(task);
        assertThat(saved).isNotNull()
                .isEqualTo(task);
    }
    @Test
    void delete_shouldReturnEmpty() {
        taskRepository.save(task);
        taskRepository.delete(task);

        Optional<Task> test = taskRepository.findById(task.getId());

        assertThat(test).isEmpty();
    }
}
