package com.project.tontine.repository;

import com.project.tontine.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>
{
    ArrayList<Member> findByIdNotIn(ArrayList<Integer> ids);
    ArrayList<Member> findByName(String name);
    ArrayList<Member> findByFirstname(String firstname);
    Optional<Member> findByNumber(Integer number);
    Optional<Member> findByEmail(String email);
    ArrayList<Member> findByPassword(String password);
    ArrayList<Member> findByEnable(Boolean enable);

    ArrayList<Member> findByEmailOrNumber(String email, Integer number);
    ArrayList<Member> findByEmailAndEnable(String email, Boolean enable);
}