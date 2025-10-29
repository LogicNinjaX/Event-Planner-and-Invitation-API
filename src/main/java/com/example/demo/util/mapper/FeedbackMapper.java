package com.example.demo.util.mapper;

import com.example.demo.dto.FeedbackDto;
import com.example.demo.dto.request.FeedbackRequest;
import com.example.demo.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "feedBackId", ignore = true)
    @Mapping(target = "feedBackBy", ignore = true)
    @Mapping(target = "event", ignore = true)
    Feedback toFeedback(FeedbackRequest request);

    @Mapping(target = "userId", source = "feedback.feedBackBy.userId")
    @Mapping(target = "fullName", source = "feedback.feedBackBy.fullName")
    @Mapping(target = "eventId", source = "feedback.event.eventId")
    FeedbackDto toFeedbackDto(Feedback feedback);

}
