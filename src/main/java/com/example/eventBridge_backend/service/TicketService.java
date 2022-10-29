package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;

import java.util.List;

public interface TicketService {

    public TicketDto saveTicket(TicketDto ticket, Long eventId, Long personId);

    public List<TicketDto> fetchTicketList();

    public TicketDto fetchTicketById(Long ticketId) throws EntityNotFoundException;

    public void deleteTicketById(Long ticketId);

    List<TicketDto> fetchTicketByPersonId(Long personId);

    List<TicketDto> fetchTicketsByEventId(Long eventId);

    public List<TicketDto> fetchTicketsByCategory(Categories categoryId);
}
