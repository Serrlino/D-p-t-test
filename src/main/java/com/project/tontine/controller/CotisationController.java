package com.project.tontine.controller;

import com.project.tontine.model.Meeting;
import com.project.tontine.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.tontine.model.Cotisation;
import com.project.tontine.service.CotisationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "cotisation")
@AllArgsConstructor
public class CotisationController
{
    private CotisationService cotisationService;

    @PostMapping
    public void create(@RequestBody Cotisation cotisation)
    {
		cotisationService.create(cotisation);
    }

    @GetMapping(path = "id")
    public Cotisation read(Map<String, Integer> ids)
    {
        return cotisationService.readByIds(ids);
    }

    @GetMapping
    public ArrayList<Cotisation> read()
    {
        return cotisationService.readAll();
    }

    // @PutMapping
    // public void update(@RequestBody Member member, @RequestBody Meeting meeting, @RequestBody Cotisation cotisation)
    // {
    //     cotisationService.updateCotisationByIds(member, meeting, cotisation);
    // }

    // @DeleteMapping(path = "id")
    // public void delete(@RequestBody Member member, @RequestBody Meeting meeting)
    // {
    //     cotisationService.deleteCotisationByIds(member, meeting);
    // }

    // @DeleteMapping
    // public void delete()
    // {
    //     cotisationService.deleteAllCotisation();
    // }
}
