package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.ToString;

@ToString
@JsonPropertyOrder({"id", "feedback", "rating"})
public class FeedbackDto {

    private int id;

    private String feedback;

    @Min(value = 1, message = "rating must be at least 1")
    @Max(value = 5, message = "rating must not exceed 5")
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
