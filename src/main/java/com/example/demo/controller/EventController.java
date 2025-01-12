package com.example.demo.controller;

import com.example.demo.dto.EventDto;
import com.example.demo.exception.EventException;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/organizer")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/{organizer-id}/events")
    public ResponseEntity<Object> saveEvent(@PathVariable("organizer-id") int organizerId, @Valid @RequestBody EventDto eventDto) {
        int id = eventService.saveEvent(organizerId, eventDto);
        eventDto.setId(id);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Event saved with id:" + eventDto.getId());
        response.put(ResponseBody.data, eventDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{organizer-id}/events/{event-id}")
    public ResponseEntity<Object> getEvent(@PathVariable("organizer-id") int organizerId, @PathVariable("event-id") int eventId) throws EventException {
        EventDto eventDto = eventService.getEvent(organizerId, eventId);
        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Event found with eventId:" + eventId);
        response.put(ResponseBody.data, eventDto);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{organizer-id}/events/{event-id}")
    public ResponseEntity<Object> updateEvent(@PathVariable("organizer-id") int organizerId, @PathVariable("event-id") int eventId, @Valid @RequestBody EventDto eventDto) throws EventException {
        eventService.updateEvent(organizerId, eventId, eventDto);
        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Event details updated with eventId:" + eventId);
        response.put(ResponseBody.data, eventService.getEvent(organizerId, eventId));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{organizer-id}/events/{event-id}")
    public ResponseEntity<Object> patchEvent(@PathVariable("organizer-id") int organizerId, @PathVariable("event-id") int eventId, @RequestBody EventDto eventDto) throws EventException {
        eventService.patchEvent(organizerId, eventId, eventDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Event details updated with eventId:" + eventId);
        response.put(ResponseBody.data, eventService.getEvent(organizerId, eventId));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{organizer-id}/events/{event-id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable("organizer-id") int organizerId, @PathVariable("event-id") int eventId) throws Exception {
        eventService.deleteEvent(organizerId, eventId);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Event deleted with eventId:" + eventId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{organizer-id}/events")
    public ResponseEntity<Object> getEvents(@PathVariable("organizer-id") int organizerId, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws EventException {

        List<EventDto> eventDtoList = eventService.getEvents(organizerId, pageNumber, pageSize);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Page No:"+pageNumber+" Page Size:"+pageSize);
        response.put(ResponseBody.data, eventDtoList);
        return ResponseEntity.ok(response);
    }

}
