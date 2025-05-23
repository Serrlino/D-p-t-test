package com.project.tontine.repository;

import com.project.tontine.model.Jwt;
import com.project.tontine.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtRepository extends JpaRepository<Jwt, Integer>
{
    Optional<Jwt> findByMemberAndDeactivation(Member member, Boolean deactivation);
    void deleteAllByDeactivation(Boolean deactivation);
    Optional<Jwt> findByValue(String value);
}