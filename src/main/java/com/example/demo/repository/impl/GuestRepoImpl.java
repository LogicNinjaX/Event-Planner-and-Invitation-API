package com.example.demo.repository.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Guest;
import com.example.demo.enumerated.RSVPStatus;
import com.example.demo.exception.EventException;
import com.example.demo.exception.GuestException;
import com.example.demo.repository.GuestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class GuestRepoImpl implements GuestRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveGuest(int eventId, Guest guest) throws EventException {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Event event = session.get(Event.class, eventId);

            if (Objects.isNull(event)) {
                throw new EventException("event not found id:" + eventId);
            }

            guest.setEvent(event);
            session.persist(guest);
            session.getTransaction().commit();

            return guest.getId();
        }
    }

    @Override
    public Guest getGuest(int eventId, int guestId) throws Exception {
        try (Session session = sessionFactory.openSession()) {

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            Guest guest = session.get(Guest.class, guestId);
            if (Objects.isNull(guest)) {
                throw new GuestException("guest not found with id:" + guestId);
            }

            return guest;
        }
    }

    @Override
    public void updateGuest(int eventId, int guestId, Guest guest) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            Guest tempObj = session.get(Guest.class, guestId);
            if (Objects.isNull(tempObj)) {
                throw new GuestException("guest not found with id:" + guestId);
            }


            tempObj.setName(guest.getName());
            tempObj.setEmail(guest.getEmail());
            tempObj.setPhone(guest.getPhone());

            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void patchGuest(int eventId, int guestId, Guest guest) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            Guest tempObj = session.get(Guest.class, guestId);
            if (Objects.isNull(tempObj)) {
                throw new GuestException("guest not found with id:" + guestId);
            }

            if (Objects.nonNull(guest.getName())) {
                tempObj.setName(guest.getName());
            }

            if (Objects.nonNull(guest.getEmail())) {
                tempObj.setEmail(guest.getEmail());
            }

            if (guest.getPhone() != 0) {
                tempObj.setPhone(guest.getPhone());
            }

            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteGuest(int eventId, int guestId) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            Guest tempObj = session.get(Guest.class, guestId);
            if (Objects.isNull(tempObj)) {
                throw new GuestException("guest not found with id:" + guestId);
            }

            session.remove(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Guest> getAllGuest(int eventId, int pageNumber, int pageSize) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Event event = session.get(Event.class, eventId);

            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            String hql = "FROM Guest g where g.event.id = ?1";

            return session.createSelectionQuery(hql, Guest.class)
                    .setParameter(1, eventId)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize).getResultList();
        }
    }

    @Override
    public void updateStatus(int eventId, int guestId, RSVPStatus rsvpStatus) throws Exception {
        try (Session session = sessionFactory.openSession()) {

            session.getTransaction().begin();

            Event event = session.get(Event.class, eventId);
            if (Objects.isNull(event)) {
                throw new EventException("event not found with id:" + eventId);
            }

            Guest tempObj = session.get(Guest.class, guestId);
            if (Objects.isNull(tempObj)) {
                throw new GuestException("guest not found with id:" + guestId);
            }

            if (tempObj.getStatus() == RSVPStatus.ACCEPTED)
            {
                throw new GuestException("status already updated");
            }

            tempObj.setStatus(rsvpStatus);

            session.merge(tempObj);

            session.getTransaction().commit();
        }
    }


}
