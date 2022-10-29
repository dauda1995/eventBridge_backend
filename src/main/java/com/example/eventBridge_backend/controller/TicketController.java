package com.example.eventBridge_backend.controller;

import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;
import com.example.eventBridge_backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("events/{eventId}/tickets")
    public ResponseEntity<TicketDto> createTicket(@PathVariable(value= "eventId") Long eventId,
                                                  @RequestParam(name= "personId") Long personId,
                                                  @RequestBody TicketDto ticketDto){

        return new ResponseEntity<>(ticketService.saveTicket(ticketDto, eventId, personId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        return new ResponseEntity<>(ticketService.fetchTicketList(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable(name = "id") Long ticketId)  {
        TicketDto ticketDto = null;
        try {
            ticketDto = ticketService.fetchTicketById(ticketId);
            return new ResponseEntity<>(ticketDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<List<TicketDto>> getTicketByPersonId(@PathVariable(name = "personId") Long personId){
        return new ResponseEntity<>(ticketService.fetchTicketByPersonId(personId),HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<TicketDto>> getTicketByEventId(@PathVariable(name = "eventId") Long eventId){
        return new ResponseEntity<>(ticketService.fetchTicketsByEventId(eventId),HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<TicketDto>> getTicketByPersonId(@RequestBody Categories categories,  @PathVariable(name = "categoryId") String categoryId){
        return new ResponseEntity<>(ticketService.fetchTicketsByCategory(categories),HttpStatus.OK);
    }




}
