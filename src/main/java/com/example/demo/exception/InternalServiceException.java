package com.example.demo.exception;

public class InternalServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InternalServiceException(String message)
    {
        super(message);
    }

}
