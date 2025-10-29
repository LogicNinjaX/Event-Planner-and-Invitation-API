package com.example.demo.exception.custom_exception;

public class UserNotFoundException extends EntityNotFoundException{

    private static final String message = "User not found with id: %s";

    public UserNotFoundException(String userId) {
        super(message.formatted(userId));
    }
}
