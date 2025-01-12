package com.example.demo.util;


import com.example.demo.dto.*;
import com.example.demo.entity.*;


public class Mapper {

    private Mapper() {

    }


    public static OrganizerDto organizerEntityToDto(Organizer organizer) {
        OrganizerDto organizerDto = new OrganizerDto();

        organizerDto.setId(organizer.getId());
        organizerDto.setName(organizer.getName());
        organizerDto.setEmail(organizer.getEmail());
        organizerDto.setPhone(organizer.getPhone());

        return organizerDto;
    }

    public static Organizer organizerDtoToEntity(OrganizerDto organizerDto) {
        Organizer organizer = new Organizer();

        organizer.setId(organizerDto.getId());
        organizer.setName(organizerDto.getName());
        organizer.setEmail(organizerDto.getEmail());
        organizer.setPhone(organizerDto.getPhone());

        return organizer;
    }

    public static EventDto eventEntityToDto(Event event) {
        EventDto eventDto = new EventDto();

        eventDto.setId(event.getId());
        eventDto.setEvent_name(event.getEvent_name());
        eventDto.setDate(event.getDate());
        eventDto.setTime(event.getTime());
        eventDto.setLocation(event.getLocation());
        eventDto.setDescription(event.getDescription());
        eventDto.setRsvp_date(event.getRsvp_date());

        //OrganizerDto organizerDto = organizerEntityToDto(event.getOrganizer());
        //eventDto.setOrganizerDto(organizerDto);

        return eventDto;
    }

    public static Event eventDtoToEntity(EventDto eventDto) {
        Event event = new Event();

        event.setId(eventDto.getId());
        event.setEvent_name(eventDto.getEvent_name());
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        event.setRsvp_date(eventDto.getRsvp_date());


        //Organizer organizer = organizerDtoToEntity(eventDto.getOrganizerDto());
        //event.setOrganizer(organizer);
        return event;
    }


    public static GuestDto guestEntityToDto(Guest guest) {
        GuestDto guestDto = new GuestDto();

        guestDto.setId(guest.getId());
        guestDto.setName(guest.getName());
        guestDto.setEmail(guest.getEmail());
        guestDto.setPhone(guest.getPhone());
        guestDto.setStatus(guest.getStatus());
        guestDto.setEmailStatus(guest.getEmailStatus());

        return guestDto;
    }


    public static Guest guestDtoToEntity(GuestDto guestDto) {
        Guest guest = new Guest();

        guest.setId(guestDto.getId());
        guest.setName(guestDto.getName());
        guest.setEmail(guestDto.getEmail());
        guest.setPhone(guestDto.getPhone());
        guest.setStatus(guestDto.getStatus());
        guest.setEmailStatus(guestDto.getEmailStatus());

        return guest;
    }

    public static FeedbackDto feedbackEntityToDto(Feedback feedback){
        FeedbackDto feedbackDto = new FeedbackDto();

        feedbackDto.setId(feedback.getId());
        feedbackDto.setFeedback(feedback.getFeedback());
        feedbackDto.setRating(feedback.getRating());

        return feedbackDto;
    }

    public static Feedback feedbackDtoToEntity(FeedbackDto feedbackDto){
        Feedback feedback = new Feedback();

        feedback.setId(feedbackDto.getId());
        feedback.setFeedback(feedbackDto.getFeedback());
        feedback.setRating(feedbackDto.getRating());

        return feedback;
    }

    public static TemplateDto templateEntityToDto(Template template){

        TemplateDto templateDto = new TemplateDto();

        templateDto.setId(template.getId());
        templateDto.setName(template.getName());
        templateDto.setContent(template.getContent());
        templateDto.setType(template.getType());

        return templateDto;
    }

    public static Template templateDtoToEntity(TemplateDto templateDto){

        Template template = new Template();

        template.setId(templateDto.getId());
        template.setName(templateDto.getName());
        template.setContent(templateDto.getContent());
        template.setType(templateDto.getType());

        return template;
    }
}
