package com.project.tontine.controller;

import com.project.tontine.model.Role;
import com.project.tontine.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Possession;
import com.project.tontine.service.PossessionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Map;


@RestController
@RequestMapping(path = "possession")
@AllArgsConstructor
public class PossessionController
{
    private PossessionService possessionService;

    @PostMapping
    public void create(@RequestBody Map<String, Integer> ids)
    {
		possessionService.create(ids);
    }

    @GetMapping(path = "id")
    public Possession read(@RequestBody Map<String, Integer> ids)
    {
        return possessionService.readByIds(ids);
    }

    @GetMapping
    public ArrayList<Possession> read()
    {
        return possessionService.readAll();
    }

    // @PutMapping
    // public void update(@RequestBody Member member, @RequestBody Role role, @RequestBody Possession possession)
    // {
    //     possessionService.updatePossessionByIds(member, role, possession);
    // }

    // @DeleteMapping(path = "id")
    // public void delete(@RequestBody Member member, @RequestBody Role role)
    // {
    //     possessionService.deletePossessionByIds(member, role);
    // }

    // @DeleteMapping
    // public void delete()
    // {
    //     possessionService.deleteAllPossession();
    // }
}
