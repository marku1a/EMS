package com.example.ems.repositories;

import com.example.ems.models.Role;
import com.example.ems.models.User;
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
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User user, user2;

    @BeforeEach
    void init() {
        Role userRole = new Role("USER");
        user = User.builder()
                .name("John")
                .surname("Wick")
                .email("Johnny@gmail.com")
                .password("asdzxc")
                .roles(List.of(userRole))
                .build();
        user.setApproved(false);
        user2 = User.builder()
                .name("Jack")
                .surname("Ryan")
                .email("JRyan@gmail.com")
                .password("qweasd")
                .roles(List.of(userRole))
                .build();
        user2.setApproved(true);
    }
    @Test
    void findById_returnNotNull() {
        userRepository.save(user);
        Optional<User> opt = userRepository.findById(user.getId());
        assertThat(opt).isNotNull();
    }
    @Test
    void findByApprovedFalse_shouldReturnOneUser() {
        userRepository.save(user);
        userRepository.save(user2);
        List<User> userList = userRepository.findByApprovedFalse();
        assertThat(userList).hasSize(1);
    }
    @Test
    void findAll_returnTwoUsers() {
        userRepository.save(user);
        userRepository.save(user2);
        List<User> userList = userRepository.findAll();
        assertThat(userList).hasSize(2);
    }
    @Test
    void save_returnUserIsSaved() {
        User saved = userRepository.save(user);
        assertThat(saved).isNotNull()
                .isEqualTo(user);
    }
    @Test
    void delete_returnIsEmpty() {
        userRepository.save(user);
        userRepository.delete(user);
        Optional<User> opt = userRepository.findById(user.getId());
        assertThat(opt).isEmpty();
    }
}
