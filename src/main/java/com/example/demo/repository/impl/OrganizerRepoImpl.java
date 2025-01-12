package com.example.demo.repository.impl;

import com.example.demo.entity.Organizer;
import com.example.demo.exception.OrganizerException;
import com.example.demo.repository.OrganizerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;


@Repository
public class OrganizerRepoImpl implements OrganizerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int saveOrganizer(Organizer organizer) {

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(organizer);
            session.getTransaction().commit();

            return organizer.getId();
        }
    }

    @Override
    public Organizer getOrganizer(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Organizer.class, id);
        }
    }

    @Override
    public void updateOrganizer(int id, Organizer organizer) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Organizer tempObj = session.get(Organizer.class, id);

            tempObj.setName(organizer.getName());
            tempObj.setPhone(organizer.getPhone());
            tempObj.setEmail(organizer.getEmail());
            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void patchOrganizer(int id, Organizer organizer) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Organizer tempObj = session.get(Organizer.class, id);

            if (Objects.isNull(tempObj)) {
                throw new OrganizerException("organizer not found");
            }

            if (organizer.getName() != null) {
                tempObj.setName(organizer.getName());
            }

            if (organizer.getEmail() != null) {
                tempObj.setEmail(organizer.getEmail());
            }

            if (organizer.getPhone() != 0) {
                tempObj.setPhone(organizer.getPhone());
            }

            session.merge(tempObj);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteOrganizer(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Organizer organizer = session.get(Organizer.class, id);

            if (Objects.isNull(organizer)){
                throw new OrganizerException("organizer not found");
            }
            session.remove(organizer);
            session.getTransaction().commit();
        }
    }


}
