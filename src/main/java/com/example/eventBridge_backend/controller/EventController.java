package com.example.eventBridge_backend.controller;

import com.example.eventBridge_backend.config.Config;
import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.CreateEventDto;
import com.example.eventBridge_backend.payload.EventDto;
import com.example.eventBridge_backend.service.EventService;
import org.hibernate.action.internal.EntityActionVetoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = Config.HOST)
@RequestMapping("/api/")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    private final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/events/createEvent/{organiserId}/events")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventDto> saveEvent(@RequestBody CreateEventDto createEventDto, @PathVariable("organiserId") Long organiserId){
        LOGGER.info("inside save event" + String.valueOf(createEventDto));
        return ResponseEntity.ok(eventService.saveEvent(createEventDto.eventDto(), organiserId));

    }
    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> fetchEventList(){
        LOGGER.info("inside of event controller");
        return ResponseEntity.ok(eventService.fetchEventList());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/events/update/{eventId}/")
    public ResponseEntity<EventDto> updateEvent(@PathVariable("eventId") Long eventId,
//                                                @PathVariable("personId") Long organiserId,
                                                @RequestBody CreateEventDto eventDt){
        EventDto eventDto = eventService.upDateEvent(eventId, eventDt.eventDto());
        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    @GetMapping("/events/getById/{eventId}")
    public ResponseEntity<EventDto> fetchEventById(@PathVariable("eventId") Long eventId) throws EntityNotFoundException {
        LOGGER.info("gotten up to here");
        return new ResponseEntity<>(eventService.fetchEventById(eventId), HttpStatus.OK);
    }

    @GetMapping("/events/byorganiser/{organiserId}")
    public ResponseEntity<List<EventDto>> fetchEventByOrganiser(@PathVariable(value = "organiserId",  required = false) Long organiser){
        return ResponseEntity.ok(eventService.fetchEventByOrganiserId(organiser));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/events/delete/{id}")
    public void deleteEventById(@PathVariable("id") Long eventId){
        eventService.deleteEventById(eventId);
        LOGGER.info("delete action successful");
    }

    @GetMapping("/events/bycategory/{category}")
    public ResponseEntity<List<EventDto>> fetchEventByCategory(@PathVariable("category") String category){
        return ResponseEntity.ok(eventService.fetchEventByCategory(category));
    }

    @GetMapping("/events/byorganiserandCategory/{organiserId}/{category}")
    public ResponseEntity<List<EventDto>> fetchEventByOrganiserAndCategory(@PathVariable(value = "organiserId",  required = false) Long organiser, @PathVariable(value = "category",  required = false) String category){
        return ResponseEntity.ok(eventService.fetchEventByPersonAndCategory(organiser, category));
    }

}
