package com.example.demo.service;

import com.example.demo.dto.FeedbackDto;
import com.example.demo.entity.Feedback;

public interface FeedbackService {

    void saveFeedback(int guestId, FeedbackDto feedbackDto) throws Exception;
    FeedbackDto getFeedback(int guestId) throws Exception;
}
