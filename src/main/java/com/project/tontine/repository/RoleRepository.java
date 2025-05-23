package com.project.tontine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.Role;
import com.project.tontine.model.Group;
import com.project.tontine.model.RoleType;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

    ArrayList<Role> findByGroup(Group group); 
    ArrayList<Role> findByRoleType(RoleType roleType);
    Optional<Role> findByGroupAndRoleType(Group group, RoleType roleType);
}
