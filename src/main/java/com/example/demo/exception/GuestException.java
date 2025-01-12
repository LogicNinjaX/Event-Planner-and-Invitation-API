package com.example.demo.exception;

public class GuestException extends Exception{
    private static final long serialVersionUID = 1L;

    public GuestException(String message)
    {
        super(message);
    }
}
