package com.project.tontine.repository;

import com.project.tontine.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.tontine.model.Account;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{
    ArrayList<Account> findByIdNotIn(ArrayList<Integer> ids);
    Optional<Account> findByUsername(String username);
    ArrayList<Account> findByEnable(Boolean enable);

    ArrayList<Account> findByMember(Member member);
}