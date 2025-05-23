package com.project.tontine.controller;

import com.project.tontine.dto.MeetingDTO;
import com.project.tontine.dto.MemberDTO;
import com.project.tontine.dto.PlannedMeetingDTO;
import com.project.tontine.model.Account;
import com.project.tontine.model.Group;
import com.project.tontine.model.Meeting;
import lombok.AllArgsConstructor;

import com.project.tontine.model.Member;
import com.project.tontine.service.MemberService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;


@RestController
@RequestMapping(path = "member")
@CrossOrigin("*")
@AllArgsConstructor
public class MemberController
{
    private MemberService memberService;

//---------------------------------------------------------------CRUD
    @PostMapping(path = "{idRoleType}")
    public Map<String, String> create(@RequestBody MemberDTO memberDTO, @PathVariable Integer idRoleType)
    {
        try
        {
          String memberNumber = memberDTO.getNumber();
          String memberEmail = memberDTO.getEmail();
          String memberPassword = memberDTO.getPassword();

          if (ControllerTester.notOnlyNumber(memberNumber))
          {
              throw new RuntimeException("Numéro de téléphone invalide");
          }

          if (!ControllerTester.isValidEmail(memberEmail))
          {
              throw new RuntimeException("Adresse mail invalide");
          }

          if (memberPassword.length() < 8)
          {
              throw new RuntimeException("Le mot de pase doit avoir au moins 8 caractères");
          }

          memberService.create(memberDTO.createMember(), idRoleType);
          return Map.of("message", "");
        }

        catch(RuntimeException re)
        {
          return Map.of("message", re.getMessage());
        }

        catch(Exception e)
        {
          return Map.of("message", "rrvrvtbt");
        }
    }

    @GetMapping(path = "id/{id}")
    public Member read(@PathVariable Integer id)
    {
        return memberService.readById(id);
    }
    
    @GetMapping
    public ArrayList<Member> read()
    {
        return memberService.readAll();
    }

    @PutMapping
    public void update(@RequestBody Member member)
    {
        memberService.update(member);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        memberService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        memberService.deleteAll();
    }
//---------------------------------------------------------------CRUD


    @PostMapping(path = "account/{idMember}")
    public void createAccount(@RequestBody Account account, @PathVariable Integer idMember)
    {
		memberService.createAccount(account, idMember);
    }

    @PostMapping(path = "group/{idMember}")
    public void createGroup(@RequestBody Group group, @PathVariable Integer idMember)
    {
		memberService.createGroup(group, idMember);
    }

    @PostMapping(path = "meeting/{idCreatorMember}")
    public void createMeeting(@RequestBody MeetingDTO meetingDTO, @PathVariable Integer idCreatorMember)
    {
		memberService.createMeeting(meetingDTO, idCreatorMember);
    }

    @PostMapping(path = "plannedMeeting/{idMember}")
    public void createPlannedMeeting(@RequestBody PlannedMeetingDTO plannedMeetingDTO, @PathVariable Integer idMember)
    {
		memberService.createPlannedMeeting(plannedMeetingDTO, idMember);
    }
}