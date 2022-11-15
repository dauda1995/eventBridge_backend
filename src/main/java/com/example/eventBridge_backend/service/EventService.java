package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Event;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.EventDto;

import java.util.List;

public interface EventService {
    public EventDto saveEvent(EventDto eventDto, Long organiserId);

    public List<EventDto> fetchEventList();

    public EventDto fetchEventById(Long eventId) throws EntityNotFoundException;

    public void deleteEventById(Long eventId);

    EventDto upDateEvent(Long eventId, Long personId, EventDto event);

    List<EventDto> fetchEventByOrganiserId(Long organiserId);

    List<EventDto> fetchEventByCategory(String category);

    List<EventDto> fetchEventByPersonAndCategory(Long organiser, String preference);

}
