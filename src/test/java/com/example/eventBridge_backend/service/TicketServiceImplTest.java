package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceImplTest {

    @Autowired
    private TicketRepository ticketRepository;



    @Test
    void saveTicket() {


    }
}