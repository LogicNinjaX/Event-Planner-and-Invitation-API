package com.example.demo.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class EventInvitationId implements Serializable {

    public UUID userId;
    private UUID eventId;

    public EventInvitationId() {
    }

    public EventInvitationId(UUID userId, UUID eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof EventInvitationId)) return false;

        EventInvitationId that = (EventInvitationId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, eventId);
    }
}
