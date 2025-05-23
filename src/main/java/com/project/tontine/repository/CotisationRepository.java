package com.project.tontine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.tontine.model.Cotisation;
import com.project.tontine.model.Account;
import com.project.tontine.model.Meeting;
import com.project.tontine.model.IdCotisation;
import java.util.List;
import java.util.Optional;

@Repository
public interface CotisationRepository extends JpaRepository<Cotisation, IdCotisation>
{
    Optional<Cotisation> findByAccountAndMeeting(Account account, Meeting meeting);
    List<Cotisation> findByAccount(Account account);
    List<Cotisation> findByMeeting(Meeting meeting);
    List<Cotisation> findByAmount(Float Amount);
}