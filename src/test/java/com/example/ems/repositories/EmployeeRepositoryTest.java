package com.example.ems.repositories;


import com.example.ems.models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee, employee2;

    @BeforeEach
    void init() {
        employee = Employee.builder()
                .name("Marko")
                .surname("Markovic")
                .email("marko@gmail.com")
                .phone("060123123")
                .department("IT")
                .salary(100000.0)
                .build();
        employee2 = Employee.builder()
                .name("Nikola")
                .surname("Nikolic")
                .email("nikola@gmail.com")
                .phone("060321321")
                .department("Sales")
                .salary(90000.0)
                .build();
    }
    //findById, findAll, save, update, delete, create
    @Test
    void findById_shouldReturnEmployee() {
        employeeRepository.save(employee);

        Optional<Employee> empCheck = employeeRepository.findById(employee.getId());

        assertThat(empCheck).isNotNull();
    }
    @Test
    void findAll_shouldReturnAllEmployees() {
        List<Employee> empList = List.of(employee, employee2);
        employeeRepository.saveAll(empList);
        List<Employee> empRepo = employeeRepository.findAll();
        assertThat(empRepo).isNotNull()
                .isEqualTo(empList);
    }
    @Test
    void save_shouldReturnSavedEmployee() {
        Employee empl = Employee.builder()
                .name("test")
                .surname("testing")
                .email("asd@gmail.com")
                .phone("12321321")
                .department("test")
                .salary(100.0)
                .build();

        Employee saved = employeeRepository.save(empl);

        assertThat(saved).isNotNull();
    }
    @Test
    void delete_shouldReturnDeleted() {
        employeeRepository.save(employee);
        employeeRepository.delete(employee);

        Optional<Employee> test = employeeRepository.findById(employee.getId());

        assertThat(test).isEmpty();
    }

}
