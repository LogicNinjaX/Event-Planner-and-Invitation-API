package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventSaveRequest {

    private String eventName;

  //  @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

  //  @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private String location;

    private String description;

    private LocalDate rsvpDate;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRsvpDate() {
        return rsvpDate;
    }

    public void setRsvpDate(LocalDate rsvpDate) {
        this.rsvpDate = rsvpDate;
    }
}
