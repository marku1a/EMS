package com.example.ems.repositories;


import com.example.ems.models.Employee;
import com.example.ems.models.Performance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PerformanceRepositoryTest {
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Performance perf, perf2;
    private Employee employee, employee2;

    @BeforeEach
    void init() {
        employee = new Employee();
        employeeRepository.save(employee);
        perf = Performance.builder()
                .employee(employee)
                .taskOne(20)
                .taskTwo(25)
                .taskNow(15)
                .taskSum(60)
                .inTimeOne(90)
                .inTimeTwo(95)
                .inTimeNow(95)
                .inTimeSum(93)
                .raise(true)
                .build();
    }
    @Test
    void findById_shouldReturnNotNull() {
        performanceRepository.save(perf);
        Optional<Performance> opt = performanceRepository.findById(perf.getId());
        assertThat(opt).isNotNull();
    }
    @Test
    void findAll_shouldReturnTwoPerformances() {
        employee2 = new Employee();
        employee2.setName("Jack Ryan");
        perf2 = Performance.builder()
                .employee(employee2)
                .taskOne(25)
                .taskTwo(20)
                .taskNow(10)
                .taskSum(55)
                .inTimeOne(89)
                .inTimeTwo(88)
                .inTimeNow(90)
                .inTimeSum(89)
                .raise(false)
                .build();
        performanceRepository.save(perf);
        performanceRepository.save(perf2);
        List<Performance> perfList = performanceRepository.findAll();
        assertThat(perfList).hasSize(2);
    }
    @Test
    void save_shouldReturnSaved() {
        Performance saved = performanceRepository.save(perf);
        assertThat(saved).isEqualTo(perf);
    }
    @Test
    void delete_shouldReturnIsEmpty() {
        performanceRepository.save(perf);
        performanceRepository.delete(perf);
        Optional<Performance> opt = performanceRepository.findById(perf.getId());
        assertThat(opt).isEmpty();
    }
    @Test
    void findByEmployeeId_shouldReturnPerformance() {
        performanceRepository.save(perf);
        Optional<Performance> empl = performanceRepository.findByEmployeeId(employee.getId());
        assertThat(empl).isNotNull()
                .containsSame(perf);
    }


}
