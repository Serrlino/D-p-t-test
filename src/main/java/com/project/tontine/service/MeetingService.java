package com.project.tontine.service;

import com.project.tontine.dto.MeetingDTO;
import com.project.tontine.model.Meeting;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.MeetingRepository;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.PlannedMeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


@AllArgsConstructor
@Service
public class MeetingService
{
    private MeetingRepository meetingRepository;

    private GroupRepository groupRepository;
    private PlannedMeetingRepository plannedMeetingRepository;
    private MemberRepository memberRepository;

    public void create(Meeting meeting)
    {
        ArrayList<Meeting> meetings = meetingRepository.findByGroup(meeting.getGroup());

        int number = meetings.stream().mapToInt(Meeting::getNumber).max().orElse(1);

        meeting.setNumber(number + 1);

        meetingRepository.save(meeting);
    }

    public void create(MeetingDTO meetingDTO)
    {
        Meeting meeting = meetingDTO.createMeeting(groupRepository, plannedMeetingRepository, memberRepository);

        ArrayList<Meeting> meetings = meetingRepository.findByGroup(meeting.getGroup());

        int number = meetings.stream().mapToInt(Meeting::getNumber).max().orElse(1);

        meeting.setNumber(number + 1);

        meetingRepository.save(meeting);
    }

    public Meeting readById(Integer id)
    {
        return meetingRepository.findById(id).orElseThrow(() -> new RuntimeException("Reunion n'existe pas présent"));
    }

    public ArrayList<Meeting> readAll()
    {
        return new ArrayList<>(meetingRepository.findAll());
    }

    public void update(Meeting meeting)
    {
        readById(meeting.getId());

        Integer meetingNumber = meeting.getNumber();
        LocalDate meetingDate = meeting.getDate();
        LocalTime meetingStartTime = meeting.getStartTime();
        LocalTime meetingMeetEndTime = meeting.getEndTime();
        Float meetingTotalAmount = meeting.getTotalAmount();

        if(meetingNumber != null)
            meeting.setNumber(meetingNumber);

        if(meetingDate != null)
            meeting.setDate(meetingDate);

        if(meetingStartTime != null)
            meeting.setStartTime(meetingStartTime);

        if(meetingMeetEndTime != null)
            meeting.setEndTime(meetingMeetEndTime);

        if(meetingTotalAmount != null)
            meeting.setTotalAmount(meetingTotalAmount);

        meetingRepository.save(meeting);
    }
  
    public void deleteById(Integer id)
    {
        readById(id);
        meetingRepository.deleteById(id);
    }

    public void deleteAll()
    {
        meetingRepository.deleteAll();
    }
}
