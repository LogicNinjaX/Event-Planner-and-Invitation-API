package com.example.demo.util.mapper;

import com.example.demo.dto.EventDto;
import com.example.demo.dto.request.EventSaveRequest;
import com.example.demo.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "invitations", ignore = true)
    @Mapping(target = "feedbackList", ignore = true)
    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event toEvent(EventSaveRequest request);


    EventDto toDto(Event event);
}
