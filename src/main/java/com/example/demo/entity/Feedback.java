package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback_table")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID feedBackId;

    private String message;

    @Column(nullable = false)
    private int rating; //1-5

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User feedBackBy;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "eventId", nullable = false)
    private Event event;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public UUID getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(UUID feedBackId) {
        this.feedBackId = feedBackId;
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

    public User getFeedBackBy() {
        return feedBackBy;
    }

    public void setFeedBackBy(User feedBackBy) {
        this.feedBackBy = feedBackBy;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
