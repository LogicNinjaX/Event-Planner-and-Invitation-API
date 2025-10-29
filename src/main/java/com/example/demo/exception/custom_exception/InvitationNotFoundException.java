package com.example.demo.exception.custom_exception;

public class InvitationNotFoundException extends EntityNotFoundException{

    private static final String message = "Invitation not found associated with user: %s and event: %s";

    public InvitationNotFoundException(String message) {
        super(message);
    }

    public InvitationNotFoundException(String userId, String eventId) {
        super(message.formatted(userId, eventId));
    }
}
