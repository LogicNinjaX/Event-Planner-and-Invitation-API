package com.example.demo.repository;

import com.example.demo.entity.Organizer;

public interface OrganizerRepository {

    int saveOrganizer(Organizer organizer);

    Organizer getOrganizer(int id);

    void updateOrganizer(int id, Organizer organizer);

    void patchOrganizer(int id, Organizer organizer);

    void deleteOrganizer(int id);
}
