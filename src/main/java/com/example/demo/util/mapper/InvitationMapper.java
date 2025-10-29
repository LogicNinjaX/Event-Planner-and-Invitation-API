package com.example.demo.util.mapper;

import com.example.demo.dto.InvitationDto;
import com.example.demo.dto.InvitationWithEventDto;
import com.example.demo.dto.InvitationWithGuestDto;
import com.example.demo.entity.EventInvitation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvitationMapper {

    @Mapping(target = "senderName", source = "eventInvitation.event.createdBy.fullName")
    @Mapping(target = "guestDetails", source = "eventInvitation.user")
    @Mapping(target = "eventDetails", source = "eventInvitation.event")
    InvitationDto toInvitationDto(EventInvitation eventInvitation);

    @Mapping(target = "senderName", source = "eventInvitation.event.createdBy.fullName")
    @Mapping(target = "guestDetails", source = "eventInvitation.user")
    InvitationWithGuestDto toInvitationWithGuestDto(EventInvitation eventInvitation);

    @Mapping(target = "senderName", ignore = true)
    @Mapping(target = "eventDetails", source = "eventInvitation.event")
    InvitationWithEventDto toInvitationWithEventDto(EventInvitation eventInvitation);
}
