package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Ticket;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.EventDto;
import com.example.eventBridge_backend.payload.PersonDto;
import com.example.eventBridge_backend.payload.TicketDto;
import com.example.eventBridge_backend.repository.EventRepository;
import com.example.eventBridge_backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public EventDto saveEvent(EventDto eventDto, Long organiserId) {

        Event event = mapToEntity(eventDto);
        Person person = userRepository.findById(organiserId).orElseThrow(() -> new RuntimeException("user not found"));
        event.setOrganiser(person);
        Event newEvent = eventRepository.save(event);

        EventDto eventResponse = mapToDto(newEvent);
        return eventResponse;
    }

    @Override
    public List<EventDto> fetchEventList() {

        List<Event> events = eventRepository.findAll();
        List<EventDto> eventDtos = events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());
        return eventDtos;
    }

    @Override
    public EventDto fetchEventById(Long eventId) throws EntityNotFoundException {
        System.out.println("check");
        Optional<Event> event = eventRepository.findById(eventId);
//        if(!event.isPresent()){
//            throw new EntityNotFoundException("event not available");
//        }
        EventDto eventDto = mapToDto(event.get());
        return eventDto;
    }

    @Override
    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);

    }

    @Override
    public EventDto upDateEvent(Long eventId, Long personId, EventDto eventDto) {
        Event eventdb = eventRepository.findById(eventId).orElseThrow(
                () -> new RuntimeException("cant find event to update"));
        Person person = userRepository.findById(personId).orElseThrow(() ->
                new RuntimeException("person not found for update"));

        if(!eventdb.getOrganiser().getPersonId().equals(person.getPersonId())){
            throw new RuntimeException("event does not belong to user");
        }

            eventdb.setEventName(eventDto.getEventName());
            eventdb.setSummary(eventDto.getSummary());
            eventdb.setCost(eventDto.getCost());
            eventdb.setAddress(eventDto.getAddress());
            eventdb.setCategories(eventDto.getCategories());
            eventdb.setCost(eventDto.getCost());
            eventdb.setEndDate(eventDto.getEndDate());
            eventdb.setEndTime(eventDto.getEndTime());
            eventdb.setStartDate(eventDto.getStartDate());
            eventdb.setStartTime(eventDto.getStartTime());
            eventdb.setImgUrl(eventDto.getImgUrl());

        Event event = eventRepository.save(eventdb);
        return mapToDto(event);
    }

    @Override
    public List<EventDto> fetchEventByOrganiserId(Long organiserId) {
        List<Event> events = eventRepository.findEventByOrganiser_PersonId(organiserId);

        List<EventDto> eventDtos = events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());

        return eventDtos;
    }

    @Override
    public List<EventDto> fetchEventByCategory(String category) {
        List<Event> events = eventRepository.findEventsByCategoriesPreference(category);
        List<EventDto> eventDtos = events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());

        return eventDtos;
    }


    public EventDto mapToDto(Event event){
        EventDto eventDto = mapper.map(event, EventDto.class);
        return eventDto;
    }

    public Event mapToEntity(EventDto eventDto){
        Event event = mapper.map(eventDto, Event.class);
        return event;
    }
}
