package com.example.demo.service.impl;

import com.example.demo.dto.GuestDto;
import com.example.demo.entity.Event;
import com.example.demo.entity.Guest;
import com.example.demo.enumerated.RSVPStatus;
import com.example.demo.exception.EventException;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GuestSerImpl implements GuestService {

    @Autowired
    private GuestRepository repository;


    @Override
    public int saveGuest(int eventId, GuestDto guestDto) throws EventException {
        return repository.saveGuest(eventId, Mapper.guestDtoToEntity(guestDto));
    }

    @Override
    public GuestDto getGuest(int eventId, int guestId) throws Exception {
        return Mapper.guestEntityToDto(repository.getGuest(eventId, guestId));
    }

    @Override
    public void updateGuest(int eventId, int guestId, GuestDto guestDto) throws Exception {
        repository.updateGuest(eventId, guestId, Mapper.guestDtoToEntity(guestDto));
    }

    @Override
    public void patchGuest(int eventId, int guestId, GuestDto guestDto) throws Exception {
        Guest guest = new Guest();

        if (Objects.nonNull(guestDto.getName())){
            guest.setName(guestDto.getName());
        }

        if (Objects.nonNull(guestDto.getEmail())){
            guest.setEmail(guestDto.getEmail());
        }

        if (guestDto.getPhone() != 0){
            guest.setPhone(guestDto.getPhone());
        }

        repository.patchGuest(eventId, guestId, guest);
    }

    @Override
    public void deleteGuest(int eventId, int guestId) throws Exception {
        repository.deleteGuest(eventId, guestId);
    }

    @Override
    public List<GuestDto> getAllGuest(int eventId, int pageNumber, int pageSize) throws Exception {
        return repository.getAllGuest(eventId,pageNumber,pageSize)
                .stream()
                .map(Mapper::guestEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(int eventId, int guestId, RSVPStatus rsvpStatus) throws Exception {
        repository.updateStatus(eventId,guestId, rsvpStatus);
    }
}
