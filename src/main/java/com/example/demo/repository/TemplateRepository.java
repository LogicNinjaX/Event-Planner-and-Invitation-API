package com.example.demo.repository;

import com.example.demo.entity.Template;
import com.example.demo.exception.TemplateException;

import java.util.List;

public interface TemplateRepository {

    List<Template> getTemplates(int pageNumber, int pageSize) throws TemplateException;
}
