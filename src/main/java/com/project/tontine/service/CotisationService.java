package com.project.tontine.service;

import com.project.tontine.model.*;
import com.project.tontine.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CotisationService{

    private CotisationRepository cotisationRepository;

    private final AccountRepository accountRepository;
    private final MeetingRepository meetingRepository;

    public void create(Cotisation cotisation)
    {
        cotisationRepository.save(cotisation);
    }

    public Cotisation readByIds(Map<String, Integer> ids)
    {
        IdCotisation idCotisation = createIdCotisation(ids);

        return cotisationRepository.findById(idCotisation).orElseThrow(() -> new RuntimeException("Cotisation n'existe pas présent"));
    }

    public ArrayList<Cotisation> readAll()
    {
        return new ArrayList<>(cotisationRepository.findAll());
    }

    // public Cotisation update(Map<String, Integer> ids, Cotisation cotisation)
    // {
    //     IdCotisation idCotisation = createIdCotisation(ids);

    //     cotisationRepository.findById(idCotisation).orElseThrow(() -> new RuntimeException("Cotisation n'existe pas présent"));

    //     Float cotisationMontant = cotisation.getCotisationMontant();

    //     cotisation.setMember(member);
    //     cotisation.setMeeting(meeting);


    //     if(cotisationMontant != null)
    //         cotisation.setCotisationMontant(cotisationMontant);

    //     return cotisationRepository.save(meeting);
    // }
  
    // public void deleteByIds(Map<String, Integer> ids)
    // {        
    //     readByIds(ids)
    //
    //     cotisationRepository.deleteById(idCotisation);
    // }

    // public void deleteAll()
    // {
    //     cotisationRepository.deleteAll();
    // }

    public IdCotisation createIdCotisation(Map<String, Integer> ids)
    {
        Optional<Account> optionalAccount = accountRepository.findById(ids.get("idMember"));
        Optional<Meeting> optionalMeeting = meetingRepository.findById(ids.get("idRole"));

        if(optionalAccount.isPresent() && optionalMeeting.isPresent())
        {
            Account account = optionalAccount.get();
            Meeting meeting = optionalMeeting.get();

            return new IdCotisation(account, meeting);
        }

        return new IdCotisation();
    }

}
