package com.example.demo.exception.custom_exception;

public class FeedbackNotFoundException extends EntityNotFoundException{

    private static final String message = "Feedback not found with id: %s";
    public FeedbackNotFoundException(String feedbackId) {
        super(message.formatted(feedbackId));
    }
}
