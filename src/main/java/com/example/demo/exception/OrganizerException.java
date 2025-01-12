package com.example.demo.exception;

public class OrganizerException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrganizerException(String message)
    {
        super(message);
    }
}
