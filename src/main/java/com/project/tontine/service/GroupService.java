package com.project.tontine.service;

import com.project.tontine.model.Group;
import com.project.tontine.enumeration.GroupType;
import com.project.tontine.enumeration.Periodicity;
import com.project.tontine.model.Member;
import com.project.tontine.model.Role;
import com.project.tontine.model.RoleType;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class GroupService{

    private final RoleTypeService roleTypeService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private GroupRepository groupRepository;
    private PossessionService possessionService;

//---------------------------------------------------------------CRUD
    public void create(Group group)
    {
        groupRepository.save(group);

        createAllRoleGroup(group);
        createPossession(group);
    }

    public Group readById(Integer id)
    {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Groupe n'existe pas présent"));
    }

    public ArrayList<Group> readAll()
    {
        return new ArrayList<>(groupRepository.findAll());
    }

    public void update(Group group)
    {
        readById(group.getId());

        String groupName = group.getName();
        String groupDescription = group.getDescription();
        Periodicity groupMeetPeriodicity = group.getMeetPeriodicity();
        GroupType groupType = group.getType();
        Float groupCeiling = group.getCeiling();

        if(groupName != null)
            group.setName(groupName);

        if(groupDescription != null)
            group.setDescription(groupDescription);

        if(groupMeetPeriodicity != null)
            group.setMeetPeriodicity(groupMeetPeriodicity);

        if(groupType != null)
            group.setType(groupType);

        if(groupCeiling != null)
            group.setCeiling(groupCeiling);

        groupRepository.save(group);
    }
  
    public void deleteById(Integer id)
    {
        readById(id);
        groupRepository.deleteById(id);
    }

    public void deleteAll()
    {
        groupRepository.deleteAll();
    }
//---------------------------------------------------------------CRUD

    //-------------------------------------------------------------------------------------1
    public void createAllRoleGroup(Group group)
    {
        ArrayList<RoleType> roleTypes = roleTypeService.readAll();

        for(RoleType roleType : roleTypes)
        {
            Role role = new Role();

            role.setGroup(group);
            role.setRoleType(roleType);

            roleRepository.save(role);
        }
    }

    public void createPossession(Group group)
    {
        ArrayList<Member> members = (ArrayList<Member>) memberRepository.findAll();
        Integer idGroup = group.getId();

        for(Member member : members)
        {
            Integer idMember = member.getId();

            Map<String, Integer> map1 = Map.of(
                "idMember", idMember,
                "idRole", roleService.readByIdGroupAndIdRoleType(idGroup, 2).getId()
            );

            Map<String, Integer> map2 = Map.of(
                "idMember", idMember,
                "idRole", roleService.readByIdGroupAndIdRoleType(idGroup, 6).getId()
            );

            if(List.of(1, 2).contains(idMember))
            {
                possessionService.create(Map.of(
                "idMember", idMember,
                "idRole", roleService.readByIdGroupAndIdRoleType(idGroup, 1).getId()
                ));
            }

            else
            {
                try
                {
                    Boolean isAdmin = member.getAuthorities().contains(possessionService.readByIds(map1));
                }

                catch(RuntimeException re)
                {
                    possessionService.create(map2);
                }
            }
        }
    }
    //-------------------------------------------------------------------------------------1
}