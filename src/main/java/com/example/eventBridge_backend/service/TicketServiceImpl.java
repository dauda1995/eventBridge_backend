package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.*;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;
import com.example.eventBridge_backend.qrcode.QRCodeGenerator;
import com.example.eventBridge_backend.repository.EventRepository;
import com.example.eventBridge_backend.repository.TicketRepository;
import com.example.eventBridge_backend.repository.UserRepository;
import com.google.zxing.WriterException;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
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



    @Autowired
    private ModelMapper mapper;

    @Override
    public TicketDto saveTicket(TicketDto ticketDto ,Long eventId, Long personId)
    {
        Ticket ticket1 = mapToEntity(ticketDto);

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new javax.persistence.EntityNotFoundException("event not found"));
        LoggerFactory.getLogger("event found").info("event found: " + event.getEventName());
        Person person = userRepository.findById(personId).orElseThrow(() -> new javax.persistence.EntityNotFoundException("person not found"));
        LoggerFactory.getLogger("event found").info("event found: " + person.getFirstName());
        ticket1.setEvent(event);
        ticket1.setCustomer(person);
        Ticket newTicket = ticketRepository.save(ticket1);

        TicketDto ticketResponse = mapToDto(newTicket);

        return ticketResponse;
    }

    @Override
    public List<TicketDto> fetchTicketList() {
        List<Ticket> tickets = ticketRepository.findAll();

        List<TicketDto> ticketDtos = tickets.stream()
                .map(ticket -> mapToDto(ticket))
                .collect(Collectors.toList());
        LoggerFactory.getLogger("ticketList found").info("event found: " + ticketDtos.get(0));

        return ticketDtos;
//
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
    public TicketDto fetchTicketByEventIdAndCustomerId(Long eventId, Long customerId) throws EntityNotFoundException {
        Ticket ticket = ticketRepository.findTicketByCustomer_PersonIdAndEvent_EventID(eventId, customerId);
//        if(ticket.isEmpty()) {
////            LoggerFactory.getLogger("kokmenl").info(ticket.get().getDateCreated() + " the ticket is not found");
//            throw new EntityNotFoundException();
//        }else {

//            LoggerFactory.getLogger("kokmental").info(ticket.get().getDateCreated() + " the ticket id");
            TicketDto ticketDto = mapToDto(ticket);
        byte[] image = new byte[0];

        String qrstring = "eventname: " +ticketDto.getEvent().getEventName() + "\n" +
        "customerId: " + ticketDto.getCustomer().getEmail() + "\n " + ticketDto.getTicketId() +
                "\n  event date: " + ticketDto.getEvent().getStartDate() + "\n " + ticketDto.getEvent().getAddress().getCity();

        try{
            image = QRCodeGenerator.getQRCodeImage(qrstring,250,250);
        }catch (WriterException | IOException e){
            e.printStackTrace();
        }
        String qrcode = Base64.getEncoder().encodeToString(image);

        ticketDto.setQrcode(qrcode);
        return ticketDto;
//        }
    }
//
//    @Override
//    public List<TicketDto> fetchTicketsByCategory(Categories category) {
//        List<Ticket> tickets = ticketRepository.findTicketsByEventCategories(category);
//
//        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());
//
//        return ticketDtos;
//    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto, Long eventId, Long id) {

        Event eventdb = eventRepository.findById(eventId).orElseThrow(
                () -> new RuntimeException("cant find event to update"));

        Ticket ticket = ticketRepository.findById(id).orElseThrow(()-> new RuntimeException());

//        Person person = userRepository.findById();
        return null;
    }

    @Override
    public List<TicketDto> fetchTicketByPersonIdAndCategory(Long personId, String category) {

        Person person = userRepository.findById(personId).orElseThrow(
                () -> new RuntimeException("cant find person")
        );

        List<Ticket> tickets = ticketRepository.findTicketsByCustomer_PersonIdAndEvent_Categories_Preference(personId, category);

        List<TicketDto> ticketDtos = tickets.stream().map(ticket -> mapToDto(ticket)).collect(Collectors.toList());

        return ticketDtos;

    }

    public TicketDto mapToDto(Ticket ticket){

        TicketDto ticketDto = mapper.map(ticket, TicketDto.class);
        LoggerFactory.getLogger("ticket found").info("event found: " + ticketDto.toString());
        return ticketDto;
    }

    public Ticket mapToEntity(TicketDto ticketDto){
       Ticket ticket = mapper.map(ticketDto, Ticket.class);
        return ticket;
    }
}
