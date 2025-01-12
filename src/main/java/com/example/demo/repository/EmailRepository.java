package com.example.demo.repository;

import com.example.demo.entity.Guest;

import java.util.List;

public interface EmailRepository {

    List<Guest> getDetails(int organizerId, int eventId, int templateId) throws Exception;
}
