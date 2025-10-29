package com.example.demo.service.impl;

import com.example.demo.dto.InvitationDto;
import com.example.demo.entity.EventInvitation;
import com.example.demo.entity.EventInvitationId;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.InvitationService;
import com.example.demo.util.mapper.InvitationMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final InvitationMapper invitationMapper;
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private String ip;


    public EmailServiceImpl(@Value("${app.ip}") String ip, JavaMailSender javaMailSender, TemplateEngine templateEngine, InvitationMapper invitationMapper) {
        this.ip = ip;
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.invitationMapper = invitationMapper;
    }


    private final String rsvpAcceptApi = "http://%s:8080/api/v1/update-rsvp/accept-rsvp?email=%s";


    @Async
    @Override
    public void sendInvitationEmail(EventInvitation invitation){
        InvitationDto invitationDto = invitationMapper.toInvitationDto(invitation);
        Context context = getEventContext(invitationDto);
        context.setVariable("rsvpLink", rsvpAcceptApi.formatted(ip, invitationDto.getGuestDetails().getEmail()));

        String htmlContent = templateEngine.process("invitation", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(invitationDto.getGuestDetails().getEmail());
            mimeMessageHelper.setSubject("Invitation for: "+invitationDto.getEventDetails().getEventName());
            mimeMessageHelper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            logger.info("Invitation email sent to: {}", invitationDto.getGuestDetails().getEmail());
        }catch (MessagingException e){
            throw new RuntimeException("Failed to send email to: "+invitationDto.getGuestDetails().getEmail());
        }
    }



    private Context getEventContext(InvitationDto invitationDto){
        Context context = new Context();
        context.setVariable("senderName", invitationDto.getSenderName());
        context.setVariable("guestName", invitationDto.getGuestDetails().getFullName());
        context.setVariable("eventName", invitationDto.getEventDetails().getEventName());
        context.setVariable("eventDate", invitationDto.getEventDetails().getDate());
        context.setVariable("eventTime", invitationDto.getEventDetails().getTime());
        context.setVariable("eventLocation", invitationDto.getEventDetails().getLocation());
        context.setVariable("eventDescription", invitationDto.getEventDetails().getDescription());
        context.setVariable("rsvpDate", invitationDto.getEventDetails().getRsvpDate());
        //context.setVariable("rsvpLink", "");

        return context;
    }
}
