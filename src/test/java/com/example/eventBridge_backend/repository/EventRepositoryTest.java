package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    @Test
    void findEventByOrganiser_PersonId() {

    }

    @Test
    void findEventsByCategoriesPreference() {

    }
}