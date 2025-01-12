package com.example.demo.repository.impl;

import com.example.demo.entity.Template;
import com.example.demo.exception.TemplateException;
import com.example.demo.repository.TemplateRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TemplateRepoImpl implements TemplateRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Template> getTemplates(int pageNumber, int pageSize) throws TemplateException {
        try(Session session = sessionFactory.openSession())
        {
            String hql = "FROM Template";

            return session.createSelectionQuery(hql,Template.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize).getResultList();
        }
    }
}
