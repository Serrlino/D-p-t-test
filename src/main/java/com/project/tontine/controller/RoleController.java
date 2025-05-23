package com.project.tontine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Role;
import com.project.tontine.service.RoleService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "role")
@AllArgsConstructor
public class RoleController
{
    private RoleService roleService;

    @PostMapping
    public void create(@RequestBody Role role)
    {
		roleService.create(role);
    }

    @GetMapping(path = "{id}")
    public Role read(@PathVariable Integer id)
    {
        return roleService.readById(id);
    }

    @GetMapping
    public ArrayList<Role> read()
    {
        return roleService.readAll();
    }

    @PutMapping
    public void update(@RequestBody Role role)
    {
        roleService.update(role);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        roleService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        roleService.deleteAll();
    }
}
