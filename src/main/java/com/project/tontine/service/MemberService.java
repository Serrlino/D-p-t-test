package com.project.tontine.service;

import com.project.tontine.dto.MeetingDTO;
import com.project.tontine.dto.PlannedMeetingDTO;
import com.project.tontine.model.Activation;
import com.project.tontine.model.Group;
import com.project.tontine.model.Member;
import com.project.tontine.model.Account;
import com.project.tontine.model.Role;
import com.project.tontine.model.Meeting;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService implements UserDetailsService
{
    private final PlannedMeetingService plannedMeetingService;
    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ActivationService activationService;

    private AccountService accountService;
    private RoleRepository roleRepository;
    private RoleTypeService roleTypeService;
    private GroupService groupService;
    private MeetingService meetingService;
    private PossessionService possessionService;

//---------------------------------------------------------------CRUD
    public void create(Member member, Integer idRoleType) {
        String crypt = bCryptPasswordEncoder.encode(member.getPassword());
        Role role = new Role();
        ArrayList<Group> groups = new ArrayList<>(groupService.readAll());

        member.setPassword(crypt);
        memberRepository.save(member);

        if(!groups.isEmpty())
        {
            for (Group group : groups)
            {
                role.setRoleType(roleTypeService.readById(idRoleType));
                role.setGroup(group);

                roleRepository.save(role);
            }

            possessionService.create(new HashMap<>(){{
                put("idMember", member.getId());
                put("idRole", role.getId());
            }});
        }

        try
        {
            if(idRoleType != 1)
            {
                activationService.create(member);
            }
        }

        catch(Exception e)
        {
            memberRepository.delete(member);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Member readById(Integer id)
    {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));
    }

    public Member readByEmail(String memberEmail)
    {
        return memberRepository.findByEmail(memberEmail).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));
    }

    public Member readByNumber(Integer memberNumber)
    {
        return memberRepository.findByNumber(memberNumber).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));
    }
    
    public ArrayList<Member> readAll()
    {
        return (ArrayList<Member>) memberRepository.findAll();
    }

    public ArrayList<Member> readAllWithoutSuperAdmin()
    {
        return memberRepository.findByIdNotIn(new ArrayList<>(){{
            add(1);
            add(2);
        }});
    }

    public void update(Member member)
    {
        readById(member.getId());

        String memberName = member.getName();
        String memberFirstname = member.getFirstname();
        Integer memberNumber = member.getNumber();
        String memberEmail = member.getEmail();
        String memberPassword = member.getPassword();

        if(memberName != null)
            member.setName(memberName);

        if(memberFirstname != null)
            member.setFirstname(memberFirstname);

        if(memberNumber != null)
            member.setNumber(memberNumber);

        if(memberEmail != null)
            member.setEmail(memberEmail);

        if(memberPassword != null)
            member.setPassword(memberPassword);

        memberRepository.save(member);
    }

    public void deleteById(Integer id)
    {
        ArrayList<Group> groups = new ArrayList<>(readById(id).getGroups());

        for(Group group : groups)
        {
            group.setMember(null);
            groupService.update(group);
        }

        memberRepository.deleteById(id);
    }

    public void deleteAll()
    {
        memberRepository.deleteAll();
    }
//---------------------------------------------------------------CRUD

    public void activate(Map<String, Integer> activationMap)
    {
        Activation activation = activationService.readByCode(activationMap.get("code"));

        if(Instant.now().isAfter(activation.getExpirationInstant()))
        {
            deleteById(activation.getMember().getId());

            throw new RuntimeException("Votre code a expiré");
        }

        Member member = readById(activation.getMember().getId());

        member.setEnable(true);
        memberRepository.save(member);
    }
//---------------------------------------------------------------------------------------
    public void createAccount(Account account, Integer idMember)
    {
        account.setMember(readById(idMember));
        accountService.create(account);
    }

    public void createGroup(Group group, Integer idMember)
    {
        group.setMember(readById(idMember));
        groupService.create(group);

        if(idMember != 1 && idMember != 2)
        {
            Role role = roleRepository.findByGroupAndRoleType(group, roleTypeService.readById(2)).get();

            possessionService.create(new HashMap<>(){{
                put("idMember",idMember);
                put("idRole", role.getId());
            }});
        }
    }

    public void createMeeting(MeetingDTO meetingDTO, Integer idCreatorMember)
    {
        meetingDTO.setCreatorMember(idCreatorMember);

        meetingService.create(meetingDTO);
    }

    public void createPlannedMeeting(PlannedMeetingDTO plannedMeetingDTO, Integer idMember)
    {
        plannedMeetingDTO.setMember(idMember);

        plannedMeetingService.create(plannedMeetingDTO);
    }
//---------------------------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return memberRepository.findByEmail(email).orElseThrow();
    }
}
