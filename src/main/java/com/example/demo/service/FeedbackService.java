package com.example.demo.service;

import com.example.demo.dto.FeedbackDto;
import com.example.demo.dto.request.FeedbackRequest;
import com.example.demo.exception.custom_exception.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    void saveFeedback(UUID guestId, UUID eventId, FeedbackRequest request) throws EntityNotFoundException;

    List<FeedbackDto> getAllFeedback(UUID organizerId, UUID eventId, Pageable pageable);
}
