package com.example.demo.exception.custom_exception;

public class EventNotFoundException extends RuntimeException {

    private static final String message = "Event not found with id: %s";
    public EventNotFoundException(String eventId) {
        super(message.formatted(eventId));
    }
}
