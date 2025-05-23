package com.project.tontine.service;

import com.project.tontine.model.RoleType;
import com.project.tontine.repository.RoleTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.project.tontine.enumeration.RoleTypeLabel;

@Service
@AllArgsConstructor
public class RoleTypeService
{
    private RoleTypeRepository roleTypeRepository;

    public void create(RoleType roleType)
    {
        roleTypeRepository.save(roleType);
    }

    public RoleType readById(Integer id)
    {
        return roleTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("RoleType n'existe pas présent"));
    }

    public RoleType readByLabel(String roleLabel)
    {
        RoleTypeLabel roleTypeLabel = RoleTypeLabel.valueOf(roleLabel.toUpperCase());

        return roleTypeRepository.findByLabel(roleTypeLabel);
    }

    public ArrayList<RoleType> readAll()
    {
        return new ArrayList<>(roleTypeRepository.findAll());
    }
  
    public void update(RoleType roleType)
    {
        readById(roleType.getId());

        RoleTypeLabel roleTypeLabel = roleType.getLabel();
        String roleTypeDescription = roleType.getDescription();

        if(roleTypeLabel != null)
            roleType.setLabel(roleTypeLabel);

        if(roleTypeDescription != null)
            roleType.setDescription(roleTypeDescription);

        roleTypeRepository.save(roleType);
    }

    public void deleteById(Integer id)
    {
        roleTypeRepository.deleteById(id);
    }

    public void deleteAll()
    {
        roleTypeRepository.deleteAll();
    }
}
