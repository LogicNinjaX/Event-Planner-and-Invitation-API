package com.example.demo.exception.custom_exception;

public class GuestNotFoundException extends RuntimeException {
  public GuestNotFoundException(String message) {
    super(message);
  }
}
