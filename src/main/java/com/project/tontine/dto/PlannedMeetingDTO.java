package com.project.tontine.dto;

import com.project.tontine.model.PlannedMeeting;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.MeetingRepository;
import com.project.tontine.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class PlannedMeetingDTO
{
    private LocalDateTime schedulingDateTime;
    private LocalDate date;
    private LocalTime startTime;
    private Boolean isDone;

    private Integer group;
    private Integer meeting;
    private Integer member;


    public PlannedMeeting createPlannedMeeting(GroupRepository groupRepository, MeetingRepository meetingRepository, MemberRepository memberRepository)
    {
        PlannedMeeting plannedMeeting = new PlannedMeeting(null, schedulingDateTime, date, startTime, isDone, null, null, null);

        groupRepository.findById(group).ifPresent(plannedMeeting::setGroup);
        meetingRepository.findById(meeting).ifPresent(plannedMeeting::setMeeting);
        memberRepository.findById(member).ifPresent(plannedMeeting::setMember);

        return plannedMeeting;
    }
}