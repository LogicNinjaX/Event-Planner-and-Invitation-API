package com.example.demo.controller;


import com.example.demo.dto.FeedbackDto;
import com.example.demo.dto.request.FeedbackRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Event Feedback Management", description = "Endpoints related to event feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;


    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @Tag(name = "Save feedback", description = "Stores user feedback for a particular event")
    @PostMapping(path = "/{eventId}/feedback", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveFeedback(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable UUID eventId,
            @RequestBody FeedbackRequest request
            ){
        feedbackService.saveFeedback(userDetails.getUserId(), eventId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @Tag(name = "Get feedback", description = "Return feedback list in pages associated with event")
    @GetMapping(path = "/{eventId}/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<FeedbackDto>>> getAllFeedback(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable UUID eventId,
            Pageable pageable
    )
    {
        var feedbackList = feedbackService.getAllFeedback(userDetails.getUserId(), eventId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Feedback fetched successfully", feedbackList));
    }
}
