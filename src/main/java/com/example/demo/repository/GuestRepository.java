package com.example.demo.repository;

import com.example.demo.entity.Guest;
import com.example.demo.enumerated.RSVPStatus;
import com.example.demo.exception.EventException;

import java.util.List;

public interface GuestRepository {

    int saveGuest(int eventId, Guest guest) throws EventException;

    Guest getGuest(int eventId, int guestId) throws Exception;

    void updateGuest(int eventId, int guestId, Guest guest) throws Exception;

    void patchGuest(int eventId, int guestId, Guest guest) throws Exception;

    void deleteGuest(int eventId, int guestId) throws Exception;

    List<Guest> getAllGuest(int eventId, int pageNumber, int pageSize) throws Exception;

    void updateStatus(int eventId, int guestId, RSVPStatus rsvpStatus) throws Exception;
}
