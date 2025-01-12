package com.example.demo.service.impl;

import com.example.demo.dto.OrganizerDto;
import com.example.demo.entity.Organizer;
import com.example.demo.exception.OrganizerException;
import com.example.demo.repository.OrganizerRepository;
import com.example.demo.service.OrganizerService;
import com.example.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class OrganizerSerImpl implements OrganizerService {

    @Autowired
    private OrganizerRepository repository;


    @Override
    public int saveOrganizer(OrganizerDto organizerDto) throws RuntimeException {
        return repository.saveOrganizer(Mapper.organizerDtoToEntity(organizerDto));
    }

    @Override
    public OrganizerDto getOrganizer(int id) throws OrganizerException{

        Organizer organizer = repository.getOrganizer(id);

        if (Objects.isNull(organizer)){
            throw new OrganizerException("organizer not found");
        }
        return Mapper.organizerEntityToDto(organizer);
    }

    @Override
    public void updateOrganizer(int id, OrganizerDto organizerDto) throws OrganizerException {
        try{
            repository.updateOrganizer(id, Mapper.organizerDtoToEntity(organizerDto));
        }catch (OrganizerException ox)
        {
            throw new OrganizerException("error occurred");
        }
    }

    @Override
    public void patchOrganizer(int id, OrganizerDto organizerDto) {

        Organizer organizer = new Organizer();

        if (organizerDto.getName() != null){
            organizer.setName(organizerDto.getName());
        }

        if (organizerDto.getEmail() != null){
            organizer.setEmail(organizerDto.getEmail());
        }


        if (organizerDto.getPhone() != 0){
            organizer.setPhone(organizerDto.getPhone());
        }

        repository.patchOrganizer(id, organizer);
    }

    @Override
    public void deleteOrganizer(int id) throws OrganizerException{
            repository.deleteOrganizer(id);
    }

}
