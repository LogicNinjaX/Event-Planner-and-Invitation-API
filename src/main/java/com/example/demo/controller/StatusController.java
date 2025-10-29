package com.example.demo.controller;

import com.example.demo.enums.RSVPStatus;
import com.example.demo.service.InvitationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1/update-rsvp")
@Tag(name = "Utility Endpoints", description = "Endpoints related to modifying email status, rsvp status")
public class StatusController {

    private final InvitationService invitationService;
    private final File file1 = new File("src/main/resources/templates/confirmation.html");
    private final File file2 = new File("src/main/resources/templates/cancellation-confirmation.html");
    String ip;

    public StatusController(@Value("${app.ip}") String ip, InvitationService invitationService){
        this.ip = ip;
        this.invitationService = invitationService;
    }

    private final String rsvpDeclineApi = "http://%s:8080/api/v1/update-rsvp/decline-rsvp?email=%s";
    private final String rsvpAcceptApi = "http://%s:8080/api/v1/update-rsvp/accept-rsvp?email=%s";

    @GetMapping("/accept-rsvp")
    public String acceptRsvp(@RequestParam String email) throws IOException {
        invitationService.updateRSVPStatus(email, RSVPStatus.ACCEPTED);
        String htmlCode = Files.readString(file1.toPath());
        return htmlCode.replace("cancel-rsvp-api",rsvpDeclineApi.formatted(ip, email));
    }

    @GetMapping("/decline-rsvp")
    public String declineRsvp(@RequestParam String email) throws IOException {
        invitationService.updateRSVPStatus(email, RSVPStatus.DECLINED);
        String htmlCode = Files.readString(file2.toPath());
        return htmlCode.replace("accept-rsvp-api", rsvpAcceptApi.formatted(ip, email));
    }
}
