package com.example.demo.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public class FeedbackDto {

    private String fullName;

    private UUID feedBackId;

    private UUID eventId;

    private UUID userId;

    private String message;

    private int rating;

    private LocalDateTime createdAt;

    public FeedbackDto(String fullName, UUID feedBackId, UUID eventId, UUID userId, String message, int rating, LocalDateTime createdAt) {
        this.fullName = fullName;
        this.feedBackId = feedBackId;
        this.eventId = eventId;
        this.userId = userId;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UUID getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(UUID feedBackId) {
        this.feedBackId = feedBackId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
