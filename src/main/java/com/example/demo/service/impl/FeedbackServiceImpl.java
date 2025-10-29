package com.example.demo.service.impl;

import com.example.demo.dto.FeedbackDto;
import com.example.demo.dto.request.FeedbackRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.Feedback;
import com.example.demo.entity.User;
import com.example.demo.exception.custom_exception.EventNotFoundException;
import com.example.demo.exception.custom_exception.GuestNotFoundException;
import com.example.demo.exception.custom_exception.UserNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FeedbackService;
import com.example.demo.util.mapper.FeedbackMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final FeedbackMapper feedbackMapper;
    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, EventRepository eventRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.feedbackMapper = feedbackMapper;
    }

    @Transactional
    @Override
    public void saveFeedback(UUID guestId, UUID eventId, FeedbackRequest request){
        Event event = eventRepository.getEventWhereGuestExist(guestId, eventId)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found in event: "+eventId));

        User guest = event.getInvitations().stream().toList().getFirst().getUser();

        Feedback feedback = feedbackMapper.toFeedback(request);
        feedback.setFeedBackBy(guest);
        feedback.setEvent(event);
        feedbackRepository.saveAndFlush(feedback);
        logger.info("Feedback saved successfully with id: {}", feedback.getFeedBackId());
    }

    @Override
    public List<FeedbackDto> getAllFeedback(UUID organizerId, UUID eventId, Pageable pageable) {
        return feedbackRepository.getAllFeedback(pageable, organizerId, eventId).stream().toList();
    }
}
