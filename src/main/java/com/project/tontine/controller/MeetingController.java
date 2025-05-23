package com.project.tontine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Meeting;
import com.project.tontine.service.MeetingService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "meeting")
@AllArgsConstructor
public class MeetingController
{
    private MeetingService meetingService;

    @PostMapping
    public void create(@RequestBody Meeting meeting)
    {
		meetingService.create(meeting);
    }

    @GetMapping(path = "{id}")
    public Meeting read(@PathVariable Integer id)
    {
        return meetingService.readById(id);
    }

    @GetMapping
    public ArrayList<Meeting> read()
    {
        return meetingService.readAll();
    }

    @PutMapping
    public void update(@RequestBody Meeting meeting)
    {
        meetingService.update(meeting);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        meetingService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        meetingService.deleteAll();
    }
}
