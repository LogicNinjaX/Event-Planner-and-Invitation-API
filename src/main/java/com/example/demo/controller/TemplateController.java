package com.example.demo.controller;


import com.example.demo.dto.TemplateDto;
import com.example.demo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    public List<TemplateDto> getTemplates(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
        return templateService.getTemplates(pageNumber, pageSize);
    }

}
