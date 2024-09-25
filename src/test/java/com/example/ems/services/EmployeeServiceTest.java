package com.example.ems.services;


import com.example.ems.models.Employee;
import com.example.ems.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    Employee employee1, employee2;

    @BeforeEach
    void init() {
        employee1 = Employee.builder()
                .id(1)
                .name("John")
                .surname("Wick")
                .email("john.wick@gmail.com")
                .phone("123123")
                .salary(100000.0)
                .department("Waste management")
                .build();
        employee2 = Employee.builder()
                .id(2)
                .name("Tony")
                .surname("Soprano")
                .email("tony.soprano@gmail.com")
                .phone("321321")
                .salary(123456.0)
                .department("Waste management")
                .build();

    }
    @Test
    void getAllEmployees_shouldReturnEmployees() {
        List<Employee> expected = List.of(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(expected);

        List<Employee> actual = employeeService.getAllEmployees();
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void findEmployeeById_shouldReturnCorrectEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee1));

        Employee actual = employeeService.findEmployeeById(1);
        assertThat(actual).isEqualTo(employee1);
    }
    @Test
    void createNewEmployee_shouldReturnCreatedEmployee() {
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee1);

        Employee actual = employeeService.createNewEmployee(employee1);

        assertThat(actual).isEqualTo(employee1);
    }
    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() {
        Employee updated = employee1;
        updated.setName("johnny");


        when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(updated);

        Employee actual = employeeService.updateEmployee(updated);

        assertThat(actual).isEqualTo(updated);
    }
    @Test
    void deleteEmployee_shouldDeleteAnEmployee() {
        when(employeeRepository.existsById(employee1.getId())).thenReturn(true);

        boolean result = employeeService.deleteEmployee(employee1.getId());

        assertThat(result).isTrue();
        verify(employeeRepository).existsById(employee1.getId());
        verify(employeeRepository).deleteById(employee1.getId());

    }
    @Test
    void deleteEmployee_shouldReturnFalseWhenEmployeeDoesNotExist() {
        when(employeeRepository.existsById(3)).thenReturn(false);

        boolean result = employeeService.deleteEmployee(3);

        assertThat(result).isFalse();
        verify(employeeRepository).existsById(3);
        verify(employeeRepository, never()).deleteById(anyInt());
    }
}
