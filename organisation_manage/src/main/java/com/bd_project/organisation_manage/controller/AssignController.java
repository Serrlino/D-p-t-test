package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Assign;
import com.bd_project.organisation_manage.service.AssignService;


import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/assign")
@AllArgsConstructor
public class AssignController {
    private AssignService assignServ;

    @PostMapping("create/{registerNumber}/{gradeId}/{copyNumber}/{teacherMain}")
    public String create(@PathVariable Integer registerNumber, @PathVariable Integer gradeId,
    @PathVariable String copyNumber, @PathVariable Boolean teacherMain)
    {
        Assign assign = assignServ.addAssign(registerNumber, gradeId, copyNumber, teacherMain);

        if(assign == null)
            return "";

        return "Ajoût effectué";
    }

    @PostMapping("create")
    public String create(@RequestBody Assign assign)
    {
        Assign assign2 = assignServ.addAssign(assign);

        if(assign2 == null)
            return "";

        return "Ajoût effectué";
    }

    @GetMapping("read/{registerNumber}/{gradeId}")
    public Assign read(@PathVariable Integer registerNumber, @PathVariable Integer gradeId)
    {
        return assignServ.findAssign(registerNumber, gradeId);
    }

    @GetMapping("read")
    public List<Assign> read()
    {
        return assignServ.findAllAssign();
    }

    @PutMapping("update/{registerNumber}/{gradeId}/{copyNumber}/{teacherMain}")
    public String update(@PathVariable Integer registerNumber, @PathVariable Integer gradeId,
    @PathVariable String copyNumber, @PathVariable Boolean teacherMain)
    {
        Assign assign = assignServ.modifyAssign(registerNumber, gradeId, copyNumber, teacherMain);

        if(assign == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("delete/{registerNumber}/{gradeId}")
    public Assign delete(@PathVariable Integer registerNumber, @PathVariable Integer gradeId)
    {
        Assign assign = assignServ.deleteAssign(registerNumber, gradeId);

        return assign;
    }

    @DeleteMapping("delete")
    public String delete()
    {
        assignServ.deleteAllAssign();

        return "Table vidée";
    }
}