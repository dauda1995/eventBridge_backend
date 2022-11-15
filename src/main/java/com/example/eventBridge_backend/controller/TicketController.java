package com.example.eventBridge_backend.controller;

import com.example.eventBridge_backend.config.Config;

import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.TicketDto;
import com.example.eventBridge_backend.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = Config.HOST)
@RestController
@RequestMapping("/api/")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    private final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    @PostMapping("/tickets/save/{eventId}/{personId}/tickets")
    public ResponseEntity<TicketDto> createTicket(@PathVariable(value= "eventId") Long eventId,
                                                  @PathVariable("personId") Long personId,
                                                  TicketDto ticketDto){
        LOGGER.info("created a ticket" + ticketDto.getDateCreated() + " " + personId + eventId);
        return new ResponseEntity<>(ticketService.saveTicket(ticketDto, eventId, personId), HttpStatus.CREATED);

    }

    @GetMapping("/all/tickets")
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        LOGGER.info("tickets gotten");
        return new ResponseEntity<>(ticketService.fetchTicketList(), HttpStatus.ACCEPTED);
    }

    @GetMapping("tickets/{id}")
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

    @GetMapping("tickets/byPersonId/{personId}")
    public ResponseEntity<List<TicketDto>> getTicketByPersonId(@PathVariable(name = "personId") Long personId){
        return new ResponseEntity<>(ticketService.fetchTicketByPersonId(personId),HttpStatus.OK);
    }

    @GetMapping("/byEventId/{eventId}")
    public ResponseEntity<List<TicketDto>> getTicketByEventId(@PathVariable(name = "eventId") Long eventId){
        return new ResponseEntity<>(ticketService.fetchTicketsByEventId(eventId),HttpStatus.OK);
    }
//
////    @GetMapping("/{categoryId}")
////    public ResponseEntity<List<TicketDto>> getTicketByCategory(@RequestBody Categories categories,  @PathVariable(name = "categoryId") String categoryId){
//        return new ResponseEntity<>(ticketService.fetchTicketsByCategory(categories),HttpStatus.OK);
//    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto,@PathVariable("eventId") Long eventId, @PathVariable("id") Long id){
        return new ResponseEntity<>(ticketService.updateTicket(ticketDto, eventId, id), HttpStatus.OK);
    }

    @GetMapping("/tickets/getbyIds/{personid}/{eventid}")
    public ResponseEntity<?> getTicketByEventAndPersonId(@PathVariable("personid") Long personId, @PathVariable("eventid") Long eventId) throws EntityNotFoundException {
        try {
            return new ResponseEntity<TicketDto>(ticketService.fetchTicketByEventIdAndCustomerId(eventId, personId),HttpStatus.OK);
        } catch (EntityNotFoundException e) {

           return new ResponseEntity<>("the resource wasn't found", HttpStatus.NO_CONTENT);
//            throw new EntityNotFoundException();
        }

    }

    @GetMapping("/tickets/getBypersonCategory/{personid}/{category}")
    public ResponseEntity<?> getTicketsByPersonAndCategory(@PathVariable("personid") Long personId,
                                                                         @PathVariable("category") String category) throws EntityNotFoundException {
        try {
            return new ResponseEntity<>(ticketService.fetchTicketByPersonIdAndCategory(personId, category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            return new ResponseEntity<>("cannot find tickets by person and category", HttpStatus.NO_CONTENT);
        }
    }





}
