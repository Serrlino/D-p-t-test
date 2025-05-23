package com.project.tontine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.RoleType;
import com.project.tontine.service.RoleTypeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "roleType")
@AllArgsConstructor
public class RoleTypeController
{
    private RoleTypeService roleTypeService;

    @PostMapping
    public void create(@RequestBody RoleType roleType)
    {
		roleTypeService.create(roleType);
    }

    @GetMapping(path = "id/{id}")
    public RoleType read(@PathVariable Integer id)
    {
        return roleTypeService.readById(id);
    }

    @GetMapping(path = "label/{roleLabel}")
    public RoleType read(@PathVariable String roleLabel)
    {
        return roleTypeService.readByLabel(roleLabel);
    }

    @GetMapping
    public ArrayList<RoleType> read()
    {
        return roleTypeService.readAll();
    }

    @PutMapping
    public void update(@RequestBody RoleType roleType)
    {
        roleTypeService.update(roleType);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        roleTypeService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        roleTypeService.deleteAll();
    }
}
