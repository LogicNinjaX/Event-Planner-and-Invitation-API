package com.example.demo.repository;


import com.example.demo.entity.Feedback;

public interface FeedbackRepository {

    void saveFeedback(int guestId, Feedback feedback) throws Exception;

    Feedback getFeedback(int guestId) throws Exception;
}
