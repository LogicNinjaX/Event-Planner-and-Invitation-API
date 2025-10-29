package com.example.demo.service.impl;

import com.example.demo.dto.InvitationDto;
import com.example.demo.dto.InvitationWithEventDto;
import com.example.demo.dto.InvitationWithGuestDto;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventInvitation;
import com.example.demo.entity.EventInvitationId;
import com.example.demo.entity.User;
import com.example.demo.enums.EmailStatus;
import com.example.demo.enums.RSVPStatus;
import com.example.demo.exception.custom_exception.EntityNotFoundException;
import com.example.demo.exception.custom_exception.EventNotFoundException;
import com.example.demo.exception.custom_exception.InvitationNotFoundException;
import com.example.demo.exception.custom_exception.UserNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.InvitationService;
import com.example.demo.util.mapper.InvitationMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService {

    private final InvitationRepository invitationRepository;
    private final InvitationMapper invitationMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private static final Logger logger = LoggerFactory.getLogger(InvitationServiceImpl.class);

    public InvitationServiceImpl(InvitationRepository invitationRepository, InvitationMapper invitationMapper, UserRepository userRepository, EventRepository eventRepository) {
        this.invitationRepository = invitationRepository;
        this.invitationMapper = invitationMapper;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public InvitationDto getInvitations(UUID userId, UUID eventId) throws EntityNotFoundException {
        EventInvitation invitation = invitationRepository.getInvitationDetails(new EventInvitationId(userId, eventId))
                .orElseThrow(() -> new InvitationNotFoundException(userId.toString(), eventId.toString()));

        return invitationMapper.toInvitationDto(invitation);
    }

    @Transactional
    @Override
    public EventInvitation createInvitation(String email, UUID eventId) throws EntityNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId.toString()));

        EventInvitation invitation = new EventInvitation(user, event);
        invitation.setInvitationId(new EventInvitationId(user.getUserId(), event.getEventId()));

        invitation.setEmailStatus(EmailStatus.PENDING);

        invitation = invitationRepository.saveAndFlush(invitation);
        logger.info("Invitation successfully created for user: {}", user.getUserId());
        return invitation;
    }


    @Override
    public List<InvitationWithEventDto> getInvitationsWithEvent(UUID guestId, Pageable pageable){
        return invitationRepository.getInvitationDetailsByUserId(guestId, pageable)
                .stream()
                .map(invitationMapper::toInvitationWithEventDto)
                .toList();

    }

    @Override
    public List<InvitationWithGuestDto> getInvitationsWithGuest(UUID eventId, Pageable pageable){
        return invitationRepository.getInvitationDetailsByEventId(eventId, pageable)
                .stream()
                .map(invitationMapper::toInvitationWithGuestDto)
                .toList();
    }

    @Override
    public void updateEmailStatus(EventInvitationId invitationId, EmailStatus status){
        invitationRepository.updateInvitationEmailStatus(invitationId, status);
        logger.info("Invitation email status updated successfully to: {} invitation id: {}", status, invitationId);
    }


    @Override
    public void updateRSVPStatus(String email, RSVPStatus status){
        EventInvitation invitation = invitationRepository.getInvitationByGuestEmail(email)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found associated with email: "+email));
        invitation.setRsvpStatus(status);
        invitation = invitationRepository.saveAndFlush(invitation);
        logger.info("Rsvp status updated successfully to: {} invitation id: {}", status, invitation.getInvitationId());
    }
}
