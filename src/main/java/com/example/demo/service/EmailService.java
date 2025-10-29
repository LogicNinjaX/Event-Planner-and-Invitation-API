package com.example.demo.service;

import com.example.demo.entity.EventInvitation;


public interface EmailService {

    void sendInvitationEmail(EventInvitation invitation);
}
