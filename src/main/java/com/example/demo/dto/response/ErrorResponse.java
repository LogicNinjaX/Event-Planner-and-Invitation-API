package com.app.parking.dto.response;

import java.time.LocalDateTime;

public class ErrorResponse<T> {

    private Boolean success;
    private String message;
    private Integer error_code;
    private T data;
    private LocalDateTime timestamp;

    public ErrorResponse(Boolean success, String message, Integer error_code, T data) {
        this.success = success;
        this.message = message;
        this.error_code = error_code;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
