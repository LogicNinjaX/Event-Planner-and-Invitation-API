package com.example.demo.controller;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.request.EventSaveRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.entity.EventInvitation;
import com.example.demo.enums.EmailStatus;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.EmailService;
import com.example.demo.service.EventService;
import com.example.demo.service.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events")
@Tag(name = "Event and Invitation Management", description = "Endpoints related to invitation sending, event listing, deleting etc.")
public class EventController {

    private final EventService eventService;
    private final InvitationService invitationService;
    private final EmailService emailService;

    public EventController(EventService eventService, InvitationService invitationService, EmailService emailService) {
        this.eventService = eventService;
        this.invitationService = invitationService;
        this.emailService = emailService;
    }


    @Operation(summary = "Save event", description = "Returns saved event details with unique event id")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<EventDto>> saveEvent(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody EventSaveRequest request)
    {
        var savedEntity = eventService.saveEvent(userDetails.getUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Event saved successfully", savedEntity));
    }

    @Operation(summary = "Get event by event id", description = "Returns event details created by organizer")
    @GetMapping(path = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<EventDto>> getEvent(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable UUID eventId
    )
    {
        var eventEntity = eventService.getEvent(userDetails.getUserId(), eventId);
        return ResponseEntity.ok(new ApiResponse<>("Event fetched successfully", eventEntity));
    }

    @Operation(summary = "Delete event by id", description = "Removes event record from database")
    @DeleteMapping(path = "/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable UUID eventId
    )
    {
        eventService.deleteEvent(userDetails.getUserId(), eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

   @Operation(summary = "Get list of events created by user", description = "Returns event details in pages")
   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse<?>> getAllEvents(
           @AuthenticationPrincipal CustomUserDetails userDetails,
           Pageable pageable
   )
   {
        var events = eventService.getEvents(userDetails.getUserId(), pageable);
        return ResponseEntity.ok(new ApiResponse<>("events fetched successfully", events));
   }


   @Operation(summary = "Get invitation details", description = "Returns invitation details associated with event")
   @GetMapping(path = "/{eventId}/invitations", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<ApiResponse<?>> getInvitationDetails(@PathVariable UUID eventId, Pageable pageable){
        var invitationList = invitationService.getInvitationsWithGuest(eventId, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("event details fetched successfully", invitationList));
   }

   @Operation(summary = "Send invitation", description = "Sends invitation message to user email")
   @PostMapping(path = "/{eventId}/invitations")
   public ResponseEntity<Void> sendInvitation(@PathVariable UUID eventId, @RequestParam String email){
        EventInvitation invitation = invitationService.createInvitation(email, eventId);
        emailService.sendInvitationEmail(invitation);
        invitationService.updateEmailStatus(invitation.getInvitationId(), EmailStatus.SENT);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
   }


}
