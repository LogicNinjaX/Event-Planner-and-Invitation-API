package com.example.demo.controller;

import com.example.demo.dto.OrganizerDto;
import com.example.demo.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;


    @PostMapping("/organizer")
    public ResponseEntity<Object> saveOrganizer(@Valid @RequestBody OrganizerDto organizerDto) {
        int id = organizerService.saveOrganizer(organizerDto);
        organizerDto.setId(id);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Organizer saved with id:"+organizerDto.getId());
        response.put(ResponseBody.data, organizerDto);

        return ResponseEntity.ok(response);
    }



    @GetMapping("/organizer/{id}")
    public ResponseEntity<Object> getOrganizer(@PathVariable int id){

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Organizer found with id:"+id);
        response.put(ResponseBody.data,organizerService.getOrganizer(id));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/organizer/{id}")
    public ResponseEntity<Object> updateOrganizer(@PathVariable int id, @Valid @RequestBody OrganizerDto organizerDto){

        organizerService.updateOrganizer(id, organizerDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Organizer details updated:"+id);
        response.put(ResponseBody.data,organizerService.getOrganizer(id));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/organizer/{id}")
    public ResponseEntity<Object> patchOrganizer(@PathVariable int id, @RequestBody OrganizerDto organizerDto){

        organizerService.patchOrganizer(id, organizerDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Organizer details updated:"+id);
        response.put(ResponseBody.data,organizerService.getOrganizer(id));

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/organizer/{id}")
    public ResponseEntity<Object> deleteOrganizer(@PathVariable int id){

        organizerService.deleteOrganizer(id);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Organizer data deleted:"+id);

        return ResponseEntity.ok(response);
    }


}
