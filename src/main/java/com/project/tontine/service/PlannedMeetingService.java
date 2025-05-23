package com.project.tontine.service;

import com.project.tontine.dto.PlannedMeetingDTO;
import com.project.tontine.model.PlannedMeeting;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.MeetingRepository;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.PlannedMeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
@Service
public class PlannedMeetingService
{
    private PlannedMeetingRepository plannedMeetingRepository;

    private GroupRepository groupRepository;
    private MeetingRepository meetingRepository;
    private MemberRepository memberRepository;

    public void create(PlannedMeeting plannedMeeting)
    { 
       plannedMeetingRepository.save(plannedMeeting);
    }

    public void create(PlannedMeetingDTO plannedMeetingDTO)
    {
        PlannedMeeting plannedMeeting = plannedMeetingDTO.createPlannedMeeting(groupRepository, meetingRepository, memberRepository);

        plannedMeetingRepository.save(plannedMeeting);
    }

    public PlannedMeeting readById(Integer id)
    {
        return plannedMeetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Reunion n'existe pas présent"));
    }

    public ArrayList<PlannedMeeting> readAll()
    {
        return new ArrayList<>(plannedMeetingRepository.findAll());
    }

    public void update(PlannedMeeting plannedMeeting)
    {
        readById(plannedMeeting.getId());

        LocalDateTime plannedMeetingSchedulingDateTime = plannedMeeting.getSchedulingDateTime();
        LocalDate plannedMeetingDate = plannedMeeting.getDate();
        LocalTime plannedMeetingStartTime = plannedMeeting.getStartTime();

        if(plannedMeetingSchedulingDateTime != null)
            plannedMeeting.setSchedulingDateTime(plannedMeetingSchedulingDateTime);

        if(plannedMeetingDate != null)
            plannedMeeting.setDate(plannedMeetingDate);

        if(plannedMeetingStartTime != null)
            plannedMeeting.setStartTime(plannedMeetingStartTime);

        plannedMeetingRepository.save(plannedMeeting);
    }
  
    public void deleteById(Integer id)
    {
        readById(id);
        plannedMeetingRepository.deleteById(id);
    }

    public void deleteAll()
    {
        plannedMeetingRepository.deleteAll();
    }
}
