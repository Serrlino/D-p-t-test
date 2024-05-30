package com.bd_project.organisation_manage.controller;



import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd_project.organisation_manage.model.Assign;
import com.bd_project.organisation_manage.service.AssignService;


import java.util.List;



@RestController
public class ServerStatus {

    @GetMapping("/server-status")
    public String run()
    {
        return "Hello world";
    }
}