package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bd_project.organisation_manage.model.Office;
import com.bd_project.organisation_manage.service.OfficeService;
import com.bd_project.organisation_manage.repository.OfficeRepository;

import java.util.List;

@RestController
@RequestMapping("/office")
@AllArgsConstructor
@CrossOrigin("*")
public class OfficeController {

    private OfficeService officeServ;
    private OfficeRepository officeRep;

    @PostMapping("/create")
    public String create(@RequestBody Office office)
    {
		Office office2 = officeServ.addOffice(office);

        if(office2 == null)
            return "";

        return "Ajoût effectué";
    }

    @PostMapping("/create/{officeName}")
    public String create(@PathVariable String officeName)
    {
		Office office = officeServ.addOffice(officeName);

        if(office == null)
            return "";

        return "Ajoût effectué";
    }

    @GetMapping("/read/name/{officeName}")
    public List<Office> read(@PathVariable String officeName)
    {
        return officeServ.findOffice(officeName);
    }

    @GetMapping("/read/id/{officeId}")
    public Office read(@PathVariable Integer officeId)
    {
        return officeServ.findOffice(officeId);
    }


    @GetMapping("/read")
    public List<Office> read()
    {
        return officeServ.findAllOffice();
    }

    @PutMapping("/update/{officeId}/{officeName}")
    public String update(@PathVariable Integer officeId, @PathVariable String officeName)
    {
		Office office = officeServ.modifyOffice(officeId, officeName);

        if(office == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{officeId}")
    public Office delete(@PathVariable Integer officeId)
    {
        Office office = officeServ.deleteOffice(officeId);

        return office;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        officeServ.deleteAllOffice();

        return "Table vidée";
    }
}
