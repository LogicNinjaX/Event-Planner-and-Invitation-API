package com.example.demo.service.impl;

import com.example.demo.dto.FeedbackDto;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.service.FeedbackService;
import com.example.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackSerImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Override
    public void saveFeedback(int guestId, FeedbackDto feedbackDto) throws Exception {
        repository.saveFeedback(guestId, Mapper.feedbackDtoToEntity(feedbackDto));
    }

    @Override
    public FeedbackDto getFeedback(int guestId) throws Exception {
        return Mapper.feedbackEntityToDto(repository.getFeedback(guestId));
    }
}
