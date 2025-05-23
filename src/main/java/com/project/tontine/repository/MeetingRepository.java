package com.project.tontine.repository;

import com.project.tontine.model.Member;
import com.project.tontine.model.PlannedMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.Meeting;
import com.project.tontine.model.Group;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer>
{

    ArrayList<Meeting> findByNumber(Integer number);
    ArrayList<Meeting> findByDate(LocalDate date); 
    ArrayList<Meeting> findByStartTime(LocalTime startTime);
    ArrayList<Meeting> findByEndTime(LocalTime endTime);
    ArrayList<Meeting> findByTotalAmount(Float totalAmount);
    
    ArrayList<Meeting> findByGroup(Group group);
    ArrayList<Meeting> findByPlannedMeeting(PlannedMeeting plannedMeeting);
    ArrayList<Meeting> findByCreatorMember(Member creatorMember);
    ArrayList<Meeting> findByRecipientMember(Member recipientMember);

    Optional<Meeting> findTopByOrderByNumberDesc();
}

