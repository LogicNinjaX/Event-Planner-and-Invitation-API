package com.example.demo.controller;


import com.example.demo.entity.Guest;
import com.example.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/organizer")
public class EmailController {

    @Autowired
    private EmailRepository repository;

    @PostMapping("/{organizerId}/events/{eventId}/template/{templateId}/send")
    public ResponseEntity<Object> getDetails(@PathVariable int organizerId, @PathVariable int eventId, @PathVariable int templateId) throws Exception {

        List<Guest> guestList = repository.getDetails(organizerId, eventId, templateId);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Email sent");
        response.put("Not sent to", guestList);

        return ResponseEntity.ok(response);
    }
}
