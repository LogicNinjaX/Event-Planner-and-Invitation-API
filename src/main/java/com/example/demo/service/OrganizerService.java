package com.example.demo.service;

import com.example.demo.dto.OrganizerDto;
import com.example.demo.exception.OrganizerException;

public interface OrganizerService {

    int saveOrganizer(OrganizerDto organizerDto);

    OrganizerDto getOrganizer(int id) throws OrganizerException;

    void updateOrganizer(int id, OrganizerDto organizerDto) throws OrganizerException;

    void patchOrganizer(int id, OrganizerDto organizerDto);

    void deleteOrganizer(int id) throws OrganizerException;

}
