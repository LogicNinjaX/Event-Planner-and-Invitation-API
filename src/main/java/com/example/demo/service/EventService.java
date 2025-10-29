package com.example.demo.service;


import com.example.demo.dto.EventDto;
import com.example.demo.dto.request.EventSaveRequest;
import com.example.demo.exception.custom_exception.EventNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EventService {

    EventDto saveEvent(UUID organizerId, EventSaveRequest request);

    EventDto getEvent(UUID organizerId, UUID eventId) throws EventNotFoundException;

    void deleteEvent(UUID organizerId, UUID eventId);

    List<EventDto> getEvents(UUID organizerId, Pageable pageable);
}
