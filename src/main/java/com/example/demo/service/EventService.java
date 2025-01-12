package com.example.demo.service;

import com.example.demo.dto.EventDto;
import com.example.demo.exception.EventException;

import java.util.List;

public interface EventService {
    int saveEvent(int organizer_id, EventDto eventDto);

    EventDto getEvent(int organizerId, int eventId) throws EventException;

    void updateEvent(int organizerId, int eventId, EventDto eventDto) throws EventException;

    void patchEvent(int organizerId, int eventId, EventDto eventDto) throws EventException;

    void deleteEvent(int organizerId, int eventId) throws Exception;

    List<EventDto> getEvents(int organizerId, int pageNumber, int pageSize) throws EventException;
}
