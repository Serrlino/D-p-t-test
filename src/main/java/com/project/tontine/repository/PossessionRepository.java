package com.project.tontine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.Possession;
import com.project.tontine.model.Member;
import com.project.tontine.model.Role;
import com.project.tontine.model.IdPossession;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PossessionRepository extends JpaRepository<Possession, IdPossession>{

    Optional<Possession> findByMemberAndRole(Member member, Role role);
    ArrayList<Possession> findByMember(Member member);
    ArrayList<Possession> findByRole(Role role);
}