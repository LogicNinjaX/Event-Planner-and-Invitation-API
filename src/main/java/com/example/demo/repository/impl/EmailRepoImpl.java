package com.example.demo.repository.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Guest;
import com.example.demo.entity.Organizer;
import com.example.demo.entity.Template;
import com.example.demo.enumerated.EmailStatus;
import com.example.demo.exception.EventException;
import com.example.demo.exception.GuestException;
import com.example.demo.exception.OrganizerException;
import com.example.demo.exception.TemplateException;
import com.example.demo.repository.EmailRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmailRepoImpl implements EmailRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmailId;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmailId);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public List<Guest> getDetails(int organizerId, int eventId, int templateId) throws Exception {
        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();


            Template template = session.get(Template.class, templateId);
            if (Objects.isNull(template)) {
                throw new TemplateException("template not found");
            }

            Organizer organizer = session.get(Organizer.class, organizerId);
            if (Objects.isNull(organizer)) {
                throw new OrganizerException("organizer not found");
            }

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found");
            }

            String hql = "FROM Guest g WHERE g.emailStatus = ?1";
            List<Guest> guestList = session.createSelectionQuery(hql, Guest.class)
                    .setParameter(1, EmailStatus.PENDING)
                    .getResultList();

            if (guestList.isEmpty()) {
                throw new GuestException("guest not found or email already sent to all guests");
            }

            /*
            String content = template.getContent();
            content = content.replace("[EventName]", event.getEvent_name());
            content = content.replace("[EventLocation]", event.getLocation());
            content = content.replace("[EventDate]", event.getDate().toString());
            content = content.replace("[RSVPDate]", event.getRsvp_date().toString());
            content = content.replace("[OrganizerName]", event.getOrganizer().getName());

            List<Guest> list = new ArrayList<>();

            for (Guest guest : guestList) {
                try {
                    content = content.replace("[GuestName]", guest.getName());
                    sendEmail(guest.getEmail(), template.getName(), content);

                    Guest g = session.get(Guest.class, guest.getId());
                    g.setEmailStatus(EmailStatus.SUCCESS);
                    session.merge(g);

                } catch (Exception e) {
                    Guest g = session.get(Guest.class, guest.getId());
                    g.setEmailStatus(EmailStatus.PENDING);
                    session.merge(g);
                    list.add(guest);
                }

            }

             */

            List<Guest> list = new ArrayList<>();

            guestList.forEach(guest -> {
                String content = template.getContent();
                content = content.replace("[EventName]", event.getEvent_name());
                content = content.replace("[EventLocation]", event.getLocation());
                content = content.replace("[EventDate]", event.getDate().toString());
                content = content.replace("[RSVPDate]", event.getRsvp_date().toString());
                content = content.replace("[OrganizerName]", event.getOrganizer().getName());

                try {
                    content = content.replace("[GuestName]", guest.getName());
                    sendEmail(guest.getEmail(), template.getName(), content);

                    Guest g = session.get(Guest.class, guest.getId());
                    g.setEmailStatus(EmailStatus.SUCCESS);
                    session.merge(g);

                } catch (Exception e) {
                    Guest g = session.get(Guest.class, guest.getId());
                    g.setEmailStatus(EmailStatus.PENDING);
                    session.merge(g);
                    list.add(guest);
                }
            });



            session.getTransaction().commit();

            return list;
        }
    }
}
