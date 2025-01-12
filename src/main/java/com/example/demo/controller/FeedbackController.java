package com.example.demo.controller;



import com.example.demo.dto.FeedbackDto;
import com.example.demo.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/guests")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/{guestId}/feedback")
    public ResponseEntity<Object> saveFeedback(@PathVariable int guestId, @Valid @RequestBody FeedbackDto feedbackDto) throws Exception {

        feedbackService.saveFeedback(guestId,feedbackDto);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "feedback submitted");
        response.put(ResponseBody.data, feedbackDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{guestId}/feedback")
    public ResponseEntity<Object> getFeedback(@PathVariable int guestId) throws Exception {

        FeedbackDto feedbackDto = feedbackService.getFeedback(guestId);

        Map<Object, Object> response = new LinkedHashMap<>();
        response.put(ResponseBody.timestamp, LocalDateTime.now());
        response.put(ResponseBody.status, HttpStatus.OK.value());
        response.put(ResponseBody.message, "feedback found");
        response.put(ResponseBody.data, feedbackDto);

        return ResponseEntity.ok(response);
    }


}
