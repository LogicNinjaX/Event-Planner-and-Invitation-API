package com.example.demo.service;

import com.example.demo.dto.InvitationDto;
import com.example.demo.dto.InvitationWithEventDto;
import com.example.demo.dto.InvitationWithGuestDto;
import com.example.demo.entity.EventInvitation;
import com.example.demo.entity.EventInvitationId;
import com.example.demo.enums.EmailStatus;
import com.example.demo.enums.RSVPStatus;
import com.example.demo.exception.custom_exception.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface InvitationService {

    InvitationDto getInvitations(UUID userId, UUID eventId) throws EntityNotFoundException;

    EventInvitation createInvitation(String email, UUID eventId) throws EntityNotFoundException;

    List<InvitationWithEventDto> getInvitationsWithEvent(UUID guestId, Pageable pageable);

    List<InvitationWithGuestDto> getInvitationsWithGuest(UUID eventId, Pageable pageable);

    void updateEmailStatus(EventInvitationId invitationId, EmailStatus status);

    void updateRSVPStatus(String email, RSVPStatus status);
}
