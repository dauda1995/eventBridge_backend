package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

    }


    @Test
    void findByFirstNameOrEmail() {
//

    }

    @Test
    void findByFirstName() {

    }

    @Test
    void existsByFirstName() {
    }
}