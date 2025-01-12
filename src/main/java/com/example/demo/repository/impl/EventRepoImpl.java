package com.example.demo.repository.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Organizer;
import com.example.demo.exception.EventException;
import com.example.demo.exception.OrganizerException;
import com.example.demo.repository.EventRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EventRepoImpl implements EventRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveEvent(int organizerId, Event event) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Organizer organizer = session.get(Organizer.class, organizerId);

            event.setOrganizer(organizer);

            if (Objects.isNull(organizer)) {
                throw new OrganizerException("organizer not found");
            }
            session.persist(event);
            session.getTransaction().commit();

            return event.getId();
        }
    }

    @Override
    public Event getEvent(int organizerId, int eventId) throws EventException {
        try (Session session = sessionFactory.openSession()) {

            String hql = "SELECT e FROM Event e WHERE e.organizer.id = ?1 AND e.id = ?2";

            try {
                Event event = session.createSelectionQuery(hql, Event.class)
                        .setParameter(1, organizerId)
                        .setParameter(2, eventId).getSingleResult();

                if (Objects.isNull(event)) {
                    throw new EventException("event not found with eventId:" + eventId);
                }
                return event;
            } catch (NoResultException exception) {
                throw new EventException("event not found with eventId:" + eventId);
            }
        }
    }

    @Override
    public void updateEvent(int organizerId, int eventId, Event event) throws EventException {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Event tempObj = session.get(Event.class, eventId);

            if (Objects.nonNull(tempObj) && tempObj.getOrganizer().getId() != organizerId) {
                throw new EventException("event not found with organizerId:" + organizerId);
            } else if (Objects.isNull(tempObj)) {
                throw new EventException("event not found with eventId:" + eventId);
            }

            tempObj.setEvent_name(event.getEvent_name());
            tempObj.setDate(event.getDate());
            tempObj.setTime(event.getTime());
            tempObj.setLocation(event.getLocation());

            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void patchEvent(int organizerId, int eventId, Event event) throws EventException {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Event tempObj = session.get(Event.class, eventId);

            if (Objects.nonNull(tempObj) && tempObj.getOrganizer().getId() != organizerId) {
                throw new EventException("event not found with organizerId:" + organizerId);
            } else if (Objects.isNull(tempObj)) {
                throw new EventException("event not found with eventId:" + eventId);
            }

            if (Objects.nonNull(event.getEvent_name())) {
                tempObj.setEvent_name(event.getEvent_name());
            }

            if (Objects.nonNull(event.getDate())) {
                tempObj.setDate(event.getDate());
            }

            if (Objects.nonNull(event.getTime())) {
                tempObj.setTime(event.getTime());
            }

            if (Objects.nonNull(event.getLocation())) {
                tempObj.setLocation(event.getLocation());
            }

            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteEvent(int organizerId, int eventId) throws Exception {
        String hql = "DELETE FROM Event e where e.organizer.id = ?1 AND e.id = ?2 ";
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Event event = session.get(Event.class,eventId);
            if (Objects.nonNull(event) && event.getOrganizer().getId() != organizerId){
                throw new OrganizerException("organizer not found with event id:"+eventId);
            }

            if (Objects.isNull(event)){
                throw new EventException("event not found with id:"+eventId);
            }

            session.remove(event);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Event> getEvents(int organizerId, int pageNumber, int pageSize) throws EventException {
        try(Session session = sessionFactory.openSession()){
            String hql = "FROM Event e WHERE e.organizer.id = ?1";

            return session.createSelectionQuery(hql, Event.class)
                    .setParameter(1, organizerId)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize).getResultList();
        }
    }


}
