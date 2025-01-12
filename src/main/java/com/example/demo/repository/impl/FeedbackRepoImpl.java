package com.example.demo.repository.impl;

import com.example.demo.entity.Feedback;
import com.example.demo.entity.Guest;
import com.example.demo.exception.FeedbackException;
import com.example.demo.exception.GuestException;
import com.example.demo.repository.FeedbackRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class FeedbackRepoImpl implements FeedbackRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveFeedback(int guestId, Feedback feedback) throws Exception {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            Guest tempObj1 = session.get(Guest.class, guestId);
            if (Objects.isNull(tempObj1)){
                throw new GuestException("guest not found with id:"+guestId);
            }

            Feedback tempObj2 = tempObj1.getFeedback();
            if (Objects.nonNull(tempObj2)){
                throw new FeedbackException("feedback already submitted");
            }

            tempObj1.setFeedback(feedback);
            session.merge(tempObj1);

            session.getTransaction().commit();
        }
    }

    @Override
    public Feedback getFeedback(int guestId) throws Exception {
        try(Session session = sessionFactory.openSession()){

            Guest guest = session.get(Guest.class, guestId);
            if (Objects.isNull(guest)){
                throw new GuestException("guest not found with id:"+guestId);
            }

            Hibernate.initialize(guest.getFeedback());

            if (Objects.isNull(guest.getFeedback())){
                throw new FeedbackException("feedback not found");
            }

            return guest.getFeedback();
        }
    }
}
