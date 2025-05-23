package com.project.tontine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Jwt;
import com.project.tontine.service.JwtService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "jwt")
@AllArgsConstructor
public class JwtController
{
    private JwtService jwtService;

    // @PostMapping
    // public void create(@RequestBody Jwt jwt)
    // {
	// 	   jwtService.create(jwt);
    // }

    @GetMapping(path = "id/{id}")
    public Jwt read(@PathVariable Integer id)
    {
        return jwtService.readById(id);
    }

    @GetMapping(path = "value/{jwtValue}")
    public Jwt read(@PathVariable String jwtValue)
    {
        return jwtService.readByValue(jwtValue);
    }

    @GetMapping
    public ArrayList<Jwt> read()
    {
        return jwtService.readAll();
    }

    // @PutMapping(path = "{id}")
    // public void update(@PathVariable Integer id, @RequestBody Jwt jwt)
    // {
    //     jwtService.update(id, jwt);
    // }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        jwtService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        jwtService.deleteAll();
    }
}