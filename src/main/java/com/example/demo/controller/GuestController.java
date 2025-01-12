package com.example.demo.controller;

import com.example.demo.dto.GuestDto;
import com.example.demo.enumerated.RSVPStatus;
import com.example.demo.exception.EventException;
import com.example.demo.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping(path = "/events")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/{eventId}/guests")
    public ResponseEntity<Object> saveGuest(@PathVariable int eventId, @Valid @RequestBody GuestDto guestDto) throws EventException {

        int id = guestService.saveGuest(eventId, guestDto);
        guestDto.setId(id);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Guest details saved");
        response.put(ResponseBody.data, guestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}/guests/{guestId}")
    public ResponseEntity<Object> getGuest(@PathVariable int eventId, @PathVariable int guestId) throws Exception {

        GuestDto guestDto = guestService.getGuest(eventId, guestId);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Guest found");
        response.put(ResponseBody.data, guestDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{eventId}/guests/{guestId}")
    public ResponseEntity<Object> updateGuest(@PathVariable int eventId, @PathVariable int guestId, @Valid @RequestBody GuestDto guestDto) throws Exception {

        guestService.updateGuest(eventId, guestId, guestDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Guest details updated");
        response.put(ResponseBody.data, guestDto);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{eventId}/guests/{guestId}")
    public ResponseEntity<Object> patchGuest(@PathVariable int eventId, @PathVariable int guestId, @RequestBody GuestDto guestDto) throws Exception {

        guestService.patchGuest(eventId, guestId, guestDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Guest details updated");
        response.put(ResponseBody.data, guestDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}/guests/{guestId}")
    public ResponseEntity<Object> deleteGuest(@PathVariable int eventId, @PathVariable int guestId) throws Exception {

        guestService.deleteGuest(eventId, guestId);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Guest deleted id:" + guestId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}/guests")
    public ResponseEntity<Object> getAllGuest(@PathVariable int eventId, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws Exception {

        List<GuestDto> guestList = guestService.getAllGuest(eventId, pageNumber, pageSize);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "Page no:" + pageNumber + " Page size:" + pageSize);
        response.put(ResponseBody.data, guestList);

        return ResponseEntity.ok(response);
    }

    public static class Status {
        private String statusValue;
    }

    @PutMapping("/{eventId}/guests/{guestId}/status")
    public ResponseEntity<Object> updateStatus(@PathVariable int eventId, @PathVariable int guestId, @RequestBody Map<String, String> requestBody) throws Exception {

        RSVPStatus status = RSVPStatus.valueOf(requestBody.get("status").toUpperCase());

        guestService.updateStatus(eventId, guestId, status);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "status updated");

        return ResponseEntity.ok(response);
    }
}
