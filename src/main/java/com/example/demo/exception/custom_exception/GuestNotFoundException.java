package com.example.demo.exception.custom_exception;

public class GuestNotFoundException extends EntityNotFoundException {
    public GuestNotFoundException(String message) {
        super(message);
    }
}
