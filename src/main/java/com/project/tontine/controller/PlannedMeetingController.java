package com.project.tontine.controller;

import com.project.tontine.model.PlannedMeeting;
import com.project.tontine.service.PlannedMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "plannedMeeting")
@AllArgsConstructor
public class PlannedMeetingController
{
    private PlannedMeetingService plannedMeetingService;

    @PostMapping
    public void create(@RequestBody PlannedMeeting plannedMeeting)
    {
		plannedMeetingService.create(plannedMeeting);
    }

    @GetMapping(path = "{id}")
    public PlannedMeeting read(@PathVariable Integer id)
    {
        return plannedMeetingService.readById(id);
    }

    @GetMapping
    public ArrayList<PlannedMeeting> read()
    {
        return plannedMeetingService.readAll();
    }

    @PutMapping
    public void update(@RequestBody PlannedMeeting plannedMeeting)
    {
        plannedMeetingService.update(plannedMeeting);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        plannedMeetingService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        plannedMeetingService.deleteAll();
    }
}