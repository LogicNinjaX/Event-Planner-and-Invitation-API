package com.example.demo.service.impl;

import com.example.demo.dto.EventDto;
import com.example.demo.entity.Event;
import com.example.demo.exception.EventException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import com.example.demo.util.Mapper;
import jakarta.persistence.Entity;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventSerImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public int saveEvent(int organizer_id, EventDto eventDto) {
        return repository.saveEvent(organizer_id, Mapper.eventDtoToEntity(eventDto));
    }

    @Override
    public EventDto getEvent(int organizerId, int eventId) throws EventException {
        return Mapper.eventEntityToDto(repository.getEvent(organizerId, eventId));
    }

    @Override
    public void updateEvent(int organizerId, int eventId, EventDto eventDto) throws EventException {
        repository.updateEvent(organizerId, eventId, Mapper.eventDtoToEntity(eventDto));
    }

    @Override
    public void patchEvent(int organizerId, int eventId, EventDto eventDto) throws EventException {
        Event event = new Event();

        if (Objects.nonNull(eventDto.getEvent_name())) {
            event.setEvent_name(eventDto.getEvent_name());
        }

        if (Objects.nonNull(eventDto.getDate())) {
            event.setDate(eventDto.getDate());
        }

        if (Objects.nonNull(eventDto.getTime())) {
            event.setTime(eventDto.getTime());
        }

        if (Objects.nonNull(eventDto.getLocation())) {
            event.setLocation(eventDto.getLocation());
        }

        repository.patchEvent(organizerId, eventId, event);
    }

    @Override
    public void deleteEvent(int organizerId, int eventId) throws Exception {
        repository.deleteEvent(organizerId, eventId);
    }

    @Override
    public List<EventDto> getEvents(int organizerId, int pageNumber, int pageSize) throws EventException {
        try {
            return repository.getEvents(organizerId,pageNumber,pageSize)
                    .stream()
                    .map(Mapper::eventEntityToDto)
                    .collect(Collectors.toList());

        }catch (EventException exception){
            throw new EventException("error occurred");
        }
    }
}
