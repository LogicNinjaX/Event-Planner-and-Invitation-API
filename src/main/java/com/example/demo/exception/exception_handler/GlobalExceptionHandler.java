package com.app.parking.exception.exception_handler;


import com.app.parking.dto.response.ErrorResponse;
import com.app.parking.exception.custom_exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> entityNotFoundErrorHandler(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse<>(false, "entity not found", HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, String>>> validationExceptionHandler(MethodArgumentNotValidException ex){

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse<>(false, "invalid request", HttpStatus.BAD_REQUEST.value(), errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<String>> handleGenericException(Exception ex) {
        ErrorResponse<String> error = new ErrorResponse<>(false, "Something went wrong: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BookingFailedException.class)
    public ResponseEntity<ErrorResponse<String>> bookingFailedHandler(BookingFailedException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse<>(false, "booking failed", HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(BalanceErrorException.class)
    public ResponseEntity<ErrorResponse<String>> errorBalanceHandler(BalanceErrorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse<>(false, "balance error", HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(exception = {FieldUniqueException.class, SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse<String>> fieldUniqueHandler(FieldUniqueException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse<>(false, "integrity violation", HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(ReviewExistException.class)
    public ResponseEntity<ErrorResponse<String>> reviewExistError(ReviewExistException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse<>(false, "review already exist", HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(CancellationFailedException.class)
    public ResponseEntity<ErrorResponse<String>> handlerFailedCancellation(CancellationFailedException ex){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse<>(false, "Failed to cancel booking", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

}
