package com.example.eventBridge_backend.payload;

import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import lombok.Data;

@Data
public class TicketDto {
    private Long ticketId;
    private Event event;
    private Person customer;
    private String dateCreated;

}
