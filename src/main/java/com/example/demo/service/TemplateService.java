package com.example.demo.service;

import com.example.demo.dto.TemplateDto;
import com.example.demo.exception.TemplateException;

import java.util.List;

public interface TemplateService {

    List<TemplateDto> getTemplates(int pageNumber, int pageSize) throws TemplateException;
}
