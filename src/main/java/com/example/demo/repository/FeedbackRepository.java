package com.example.demo.repository;


import com.example.demo.dto.FeedbackDto;
import com.example.demo.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    @Query("""
            SELECT new com.example.demo.dto.FeedbackDto
            (
            fb.feedBackBy.fullName,
            fb.feedBackId,
            fb.event.eventId,
            fb.feedBackBy.userId,
            fb.message,
            fb.rating,
            fb.createdAt
            )
            FROM Feedback fb
            WHERE fb.event.createdBy.userId = :organizerId AND fb.event.eventId = :eventId
            """)
    Page<FeedbackDto> getAllFeedback(Pageable pageable, UUID organizerId, UUID eventId);
}
