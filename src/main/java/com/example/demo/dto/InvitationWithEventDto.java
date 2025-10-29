package com.example.demo.dto;

import com.example.demo.entity.EventInvitationId;
import com.example.demo.enums.EmailStatus;
import com.example.demo.enums.RSVPStatus;

public class InvitationWithEventDto {

    private EventInvitationId invitationId;

    private String senderName;

    private EventDetailsDto eventDetails;

    private EmailStatus emailStatus;

    private RSVPStatus rsvpStatus;

    public EventInvitationId getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(EventInvitationId invitationId) {
        this.invitationId = invitationId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public EventDetailsDto getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetailsDto eventDetails) {
        this.eventDetails = eventDetails;
    }

    public EmailStatus getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }

    public RSVPStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }
}
