package com.project.tontine.repository;

import com.project.tontine.model.Group;
import com.project.tontine.model.Meeting;
import com.project.tontine.model.PlannedMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public interface PlannedMeetingRepository extends JpaRepository<PlannedMeeting, Integer>
{
    ArrayList<PlannedMeeting> findBySchedulingDateTime(LocalDateTime schedulingDateTime);
    ArrayList<PlannedMeeting> findByDate(LocalDate date);
    ArrayList<PlannedMeeting> findByStartTime(LocalTime startTime);

    PlannedMeeting findByGroup(Group group);
    PlannedMeeting findByMeeting(Meeting meeting);
}
