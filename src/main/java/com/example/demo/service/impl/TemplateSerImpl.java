package com.example.demo.service.impl;

import com.example.demo.dto.TemplateDto;
import com.example.demo.exception.TemplateException;
import com.example.demo.repository.TemplateRepository;
import com.example.demo.service.TemplateService;
import com.example.demo.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateSerImpl implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Override
    public List<TemplateDto> getTemplates(int pageNumber, int pageSize) throws TemplateException {
        return repository.getTemplates(pageNumber,pageSize).stream().map(Mapper::templateEntityToDto).collect(Collectors.toList());
    }
}
