package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@JsonPropertyOrder({"id", "event_name", "date", "time", "location", "rsvp_date"})
public class EventDto {
    private int id;

    @NotBlank(message = "event name is required")
    private String event_name;

    @NotNull(message = "Event date cannot be null")
    private LocalDate date;

    @NotNull(message = "Event time cannot be null")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotBlank(message = "event location in required")
    private String location;

    @NotBlank(message = "event description in required")
    private String description;

    @NotNull(message = "Event rsvp date cannot be null")
    private LocalDate rsvp_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public LocalDate getRsvp_date() {
        return rsvp_date;
    }

    public void setRsvp_date(LocalDate rsvp_date) {
        this.rsvp_date = rsvp_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
