package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.exception.EventException;

import java.util.List;

public interface EventRepository {

    int saveEvent(int organizerId, Event event);

    Event getEvent(int organizerId, int eventId) throws EventException;

    void updateEvent(int organizerId, int eventId, Event event) throws EventException;

    void patchEvent(int organizerId, int eventId, Event event) throws EventException;

    void deleteEvent(int organizerId, int eventId) throws Exception;

    List<Event> getEvents(int organizerId, int pageNumber, int pageSize) throws EventException;
}
