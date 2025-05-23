package com.project.tontine.service;

import com.project.tontine.model.Group;

import com.project.tontine.model.RoleType;
import com.project.tontine.repository.GroupRepository;
import com.project.tontine.repository.RoleTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.project.tontine.repository.RoleRepository;
import com.project.tontine.model.Role;

@Service
@AllArgsConstructor
public class RoleService
{
    private final GroupRepository groupRepository;
    private final RoleTypeRepository roleTypeRepository;
    private RoleRepository roleRepository;
    // private GrantService grantService;
    // private PermissionService permissionService;

    public void create(Role role)
    {
        roleRepository.save(role);
    }

    public Role readById(Integer id)
    {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role n'existe pas présent"));
    }

    public Role readByIdGroupAndIdRoleType(Integer idGroup, Integer idRoleType)
    {
        return roleRepository.findByGroupAndRoleType(groupRepository.findById(idGroup).get(), roleTypeRepository.findById(idRoleType).get())
                .orElseThrow(() -> new RuntimeException("Role n'existe pas présent"));
    }

    public ArrayList<Role> readAll()
    {
        return  (ArrayList<Role>) roleRepository.findAll();
    }
  
    public void update(Role role)
    {
        readById(role.getId());

        RoleType roleType = role.getRoleType();
        Group group = role.getGroup();

        if(roleType != null)
            role.setRoleType(roleType);

        if(group != null)
            role.setGroup(group);

        roleRepository.save(role);
    }

    public void deleteById(Integer id)
    {
        readById(id);
        roleRepository.deleteById(id);
    }

    public void deleteAll()
    {
        roleRepository.deleteAll();
    }



    // public void createGrantByRole(Role role)
    // {        
    //     RoleTypeLabel roleTypeLabel = role.getRoleType().getRoleTypeLabel();
    //     Grant grant = new Grant();
    //     Permission permission = null;

    //     switch(roleTypeLabel)
    //     {
    //         case SUPER_ADMIN :
    //             permission = permissionService.readPermissionByLabel("ALL");

    //             grant.setRole(role);
    //             grant.setPermission(permission);

    //             grantService.createGrant(grant);
    //             break;
            
    //         case ADMIN :
    //             for(String permissionLabel : ArrayList.of("SELF", "ALL_GROUP", "ALL_MEETING", "ALL_PLANNED_MEETING", "ALL_COTISATION", "ALL_POSSESSION"))
    //             {
    //                 permission = permissionService.readPermissionByLabel(permissionLabel);

    //                 grant.setRole(role);
    //                 grant.setPermission(permission);

    //                 grantService.createGrant(grant);
    //             }
    //             break;
                
    //         case PRESIDENT :
    //             for(String permissionLabel : ArrayList.of("SELF", "ALL_GROUP", "ALL_MEETING", "ALL_PLANNED_MEETING", "ALL_POSSESSION"))
    //             {
    //                 permission = permissionService.readPermissionByLabel(permissionLabel);

    //                 grant.setRole(role);
    //                 grant.setPermission(permission);

    //                 grantService.createGrant(grant);
    //             }
    //             break;

    //         case SECRETARY :
    //             for(String permissionLabel : ArrayList.of("SELF", "ALL_MEETING", "ALL_PLANNED_MEETING", "ALL_POSSESSION"))
    //             {
    //                 permission = permissionService.readPermissionByLabel(permissionLabel);

    //                 grant.setRole(role);
    //                 grant.setPermission(permission);

    //                 grantService.createGrant(grant);
    //             }
    //             break;

    //         case TREASURER :
    //             for(String permissionLabel : ArrayList.of("SELF", "ALL_PLANNED_MEETING", "ALL_COTISATION"))
    //             {
    //                 permission = permissionService.readPermissionByLabel(permissionLabel);

    //                 grant.setRole(role);
    //                 grant.setPermission(permission);

    //                 grantService.createGrant(grant);
    //             }
    //             break;

    //         case MEMBER :
    //             for(String permissionLabel : ArrayList.of("SELF"))
    //             {
    //                 permission = permissionService.readPermissionByLabel(permissionLabel);

    //                 grant.setRole(role);
    //                 grant.setPermission(permission);

    //                 grantService.createGrant(grant);
    //             }
    //             break;

    //         default: //LOCKED
    //             break;
    //     }
    // }
}
