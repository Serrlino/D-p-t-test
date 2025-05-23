package com.project.tontine.service;

import com.project.tontine.model.Possession;
import com.project.tontine.model.IdPossession;
import com.project.tontine.model.Role;
import com.project.tontine.model.Member;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.PossessionRepository;
import com.project.tontine.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PossessionService
{
    private PossessionRepository possessionRepository;

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

//---------------------------------------------------------------CRUD
    public void create(Map<String, Integer> ids)
    {
        IdPossession idPossession = createIdPossession(ids);

        Possession possession = new Possession(idPossession.getMember(), idPossession.getRole());

        possessionRepository.save(possession);
    }

    public Possession readByIds(Map<String, Integer> ids)
    {
        IdPossession idPossession = createIdPossession(ids);

        return possessionRepository.findById(idPossession).orElseThrow(() -> new RuntimeException("Possession n'existe pas"));
    }

    public ArrayList<Possession> readAll()
    {
        return (ArrayList<Possession>) possessionRepository.findAll();
    }

    // public Possession update(Map<String, Integer> ids, Possession possession)
    // {
    //     IdPossession idPossession = createIdPossession(ids);

    //     possessionRepository.findById(idPossession).orElseThrow(() -> new RuntimeException("Possession n'existe pas"));

    //     Float possessionMontant = possession.getPossessionMontant();

    //     possession.setMember(member);
    //     possession.setRole(role);


    //     if(possessionMontant != null)
    //         possession.setPossessionMontant(possessionMontant);

    //     return possessionRepository.save(role);
    // }
  
    // public void deleteByIds(Map<String, Integer> ids)
    // {        
    //     IdPossession idPossession = createIdPossession(ids);

    //     possessionRepository.findById(idPossession).orElseThrow(() -> new RuntimeException("possession n'existe pas"));

    //     possessionRepository.delete(idPossession);
    // }

    // public void delete()
    // {
    //     possessionRepository.delete();
    // }
//---------------------------------------------------------------CRUD

    public IdPossession createIdPossession(Map<String, Integer> ids)
    {
        Optional<Member> optionalMember = memberRepository.findById(ids.get("idMember"));
        Optional<Role> optionalRole = roleRepository.findById(ids.get("idRole"));

        if(optionalMember.isPresent() && optionalRole.isPresent())
        {
            Member member = optionalMember.get();
            Role role = optionalRole.get();

            return new IdPossession(member, role);
        }

        return new IdPossession();
    }
}