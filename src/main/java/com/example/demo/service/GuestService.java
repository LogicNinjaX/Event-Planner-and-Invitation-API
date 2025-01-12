package com.example.demo.service;

import com.example.demo.dto.GuestDto;
import com.example.demo.entity.Guest;
import com.example.demo.enumerated.RSVPStatus;
import com.example.demo.exception.EventException;

import java.util.List;

public interface GuestService {
    int saveGuest(int eventId, GuestDto guestDto) throws EventException;

    GuestDto getGuest(int eventId, int guestId) throws Exception;

    void updateGuest(int eventId, int guestId, GuestDto guestDto) throws Exception;

    void patchGuest(int eventId, int guestId, GuestDto guestDto) throws Exception;

    void deleteGuest(int eventId, int guestId) throws Exception;

    List<GuestDto> getAllGuest(int eventId, int pageNumber, int pageSize) throws Exception;

    void updateStatus(int eventId, int guestId, RSVPStatus rsvpStatus) throws Exception;
}
