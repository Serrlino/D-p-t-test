package com.project.tontine.repository;

import com.project.tontine.enumeration.GroupType;
import com.project.tontine.enumeration.Periodicity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.Group;
import com.project.tontine.model.Member;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>
{

    ArrayList<Group> findByName(String name); 
    ArrayList<Group> findByDescription(String description);
    ArrayList<Group> findByMeetPeriodicity(Periodicity meetPeriodicity);
    ArrayList<Group> findByType(GroupType type);
    ArrayList<Group> findByCeiling(Float ceiling);

    ArrayList<Group> findByMember(Member member);
}

