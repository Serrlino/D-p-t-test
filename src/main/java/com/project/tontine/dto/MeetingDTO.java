package com.project.tontine.dto;

import com.project.tontine.model.Meeting;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.PlannedMeetingRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MeetingDTO
{

    private Integer number;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Float totalAmount;

    private Integer group;
    private Integer plannedMeeting;
    private Integer creatorMember;
    private Integer recipientMember;


    public Meeting createMeeting(GroupRepository groupRepository, PlannedMeetingRepository plannedMeetingRepository, MemberRepository memberRepository)
    {
        Meeting meeting = new Meeting(null, number, date, startTime, endTime, totalAmount, null, null, null, null, null);

        groupRepository.findById(group).ifPresent(meeting::setGroup);
        plannedMeetingRepository.findById(plannedMeeting).ifPresent(meeting::setPlannedMeeting);
        memberRepository.findById(creatorMember).ifPresent(meeting::setCreatorMember);
        memberRepository.findById(recipientMember).ifPresent(meeting::setRecipientMember);

        return meeting;
    }
}