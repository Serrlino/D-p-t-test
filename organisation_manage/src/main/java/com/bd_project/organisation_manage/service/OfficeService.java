package com.bd_project.organisation_manage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.bd_project.organisation_manage.repository.OfficeRepository;
import com.bd_project.organisation_manage.model.Office;

@Service
@AllArgsConstructor
public class OfficeService{

    private OfficeRepository officeRep;

    public Office addOffice(String officeName)
    {
        Office office = new Office();

        office.setOfficeName(officeName);

        return officeRep.save(office);
    }

    public Office addOffice(Office office)
    {
        return officeRep.save(office);
    }


    public Office findOffice(Integer officeId)
    {
        Office office = officeRep.findByOfficeId(officeId);

        return office;
    }

    public List<Office> findOffice(String officeName)
    {
        List<Office> office = officeRep.findByOfficeName(officeName);

        return office;
    }

    public List<Office> findAllOffice()
    {
        List<Office> offices = officeRep.findAll();

        return offices;
    }
  
    public Office modifyOffice(Integer officeId, String officeName)
    {
        Office office = officeRep.findByOfficeId(officeId);

        office.setOfficeName(officeName);

        return officeRep.save(office);
    }

    public Office deleteOffice(Integer officeId)
    {
        Office office = officeRep.findByOfficeId(officeId);
        officeRep.deleteById(officeId);

        return office;
    }

    public void deleteAllOffice()
    {
        officeRep.deleteAll();
    }

}