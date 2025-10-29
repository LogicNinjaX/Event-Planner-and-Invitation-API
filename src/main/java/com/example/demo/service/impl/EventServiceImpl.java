package com.example.demo.service.impl;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.request.EventSaveRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.exception.custom_exception.EventNotFoundException;
import com.example.demo.exception.custom_exception.UserNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import com.example.demo.util.mapper.EventMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;
    private final InvitationRepository invitationRepository;
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, EventMapper eventMapper, InvitationRepository invitationRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
        this.invitationRepository = invitationRepository;
    }


    @Override
    public EventDto saveEvent(UUID organizerId, EventSaveRequest request) {
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new UserNotFoundException(organizerId.toString()));
        Event event = eventMapper.toEvent(request);
        event.setCreatedBy(organizer);
        event = eventRepository.save(event);
        logger.info("Event with id: {} saved successfully", event.getEventId());
        return eventMapper.toDto(event);
    }


    @Override
    public EventDto getEvent(UUID organizerId, UUID eventId) throws EventNotFoundException {
        Event event = eventRepository.getEvent(organizerId, eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId.toString()));

        logger.info("Event with id: {} fetched successfully", eventId);
        return eventMapper.toDto(event);
    }


    @Transactional
    @Override
    public void deleteEvent(UUID organizerId, UUID eventId) {
        invitationRepository.deleteByEventId(eventId);
        eventRepository.deleteEvent(organizerId, eventId);
        logger.info("Event record deleted successfully");
    }

    @Override
    public List<EventDto> getEvents(UUID organizerId, Pageable pageable){
        return eventRepository.getAllEvents(pageable, organizerId)
                .stream().map(eventMapper::toDto).toList();
    }
}
