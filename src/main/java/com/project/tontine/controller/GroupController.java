package com.project.tontine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Group;
import com.project.tontine.service.GroupService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "group")
@AllArgsConstructor
public class GroupController
{
    private GroupService groupService;

    @PostMapping
    public void create(@RequestBody Group group)
    {
		groupService.create(group);
    }

    @GetMapping(path = "{id}")
    public Group read(@PathVariable Integer id)
    {
        return groupService.readById(id);
    }

    @GetMapping
    public ArrayList<Group> read()
    {
        return groupService.readAll();
    }

    @PutMapping
    public void update(@RequestBody Group group)
    {
        groupService.update(group);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        groupService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        groupService.deleteAll();
    }
}