package com.project.tontine.repository;

import com.project.tontine.enumeration.RoleTypeLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.RoleType;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RoleTypeRepository extends JpaRepository<RoleType, Integer>{

    RoleType findByLabel(RoleTypeLabel label);
    ArrayList<RoleType> findByDescription(String description); 
}
