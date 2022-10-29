package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;
import com.example.eventBridge_backend.repository.EventRepository;
import com.example.eventBridge_backend.repository.TicketRepository;
import com.example.eventBridge_backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    private ModelMapper mapper;

    @Override
    public TicketDto saveTicket(TicketDto ticketDto ,Long eventId, Long personId)
    {
        Ticket ticket1 = mapToEntity(ticketDto);

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("event not found"));
        Person person = userRepository.findById(personId).orElseThrow(() -> new RuntimeException("person not found"));
        ticket1.setEvent(event);
        ticket1.setCustomer(person);
        Ticket newTicket = ticketRepository.save(ticket1);

        TicketDto ticketResponse = mapToDto(newTicket);

        return ticketResponse;
    }

    @Override
    public List<TicketDto> fetchTicketList() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());

        return ticketDtos;
    }

    @Override
    public TicketDto fetchTicketById(Long ticketId) throws EntityNotFoundException {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(!ticket.isPresent()){
            throw new EntityNotFoundException("ticket not available");
        }
        TicketDto ticketDto = mapToDto(ticket.get());
        return ticketDto;
    }

    @Override
    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);

    }

    @Override
    public List<TicketDto> fetchTicketByPersonId(Long personId) {
        List<Ticket> tickets = ticketRepository.findTicketsByCustomer_PersonId(personId);

        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());

        return ticketDtos;
    }

    @Override
    public List<TicketDto> fetchTicketsByEventId(Long eventId) {
        List<Ticket> tickets = ticketRepository.findTicketsByEventEventID(eventId);

        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());

        return ticketDtos;
    }

    @Override
    public List<TicketDto> fetchTicketsByCategory(Categories category) {
        List<Ticket> tickets = ticketRepository.findTicketsByEventCategories(category);

        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());

        return ticketDtos;
    }

    public TicketDto mapToDto(Ticket ticket){
        TicketDto ticketDto = mapper.map(ticket, TicketDto.class);
        return ticketDto;
    }

    public Ticket mapToEntity(TicketDto ticketDto){
       Ticket ticket = mapper.map(ticketDto, Ticket.class);
        return ticket;
    }
}
