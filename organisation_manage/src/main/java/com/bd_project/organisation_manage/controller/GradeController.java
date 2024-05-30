package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Grade;
import com.bd_project.organisation_manage.service.GradeService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/grade")
@AllArgsConstructor
public class GradeController {
    private GradeService gradeServ;

    @PostMapping("/create/{gradeName}/{inventory}")
    public String create(@PathVariable String gradeName, @PathVariable Integer inventory)
    {
		Grade grade = gradeServ.addGrade(gradeName, inventory);

        if(grade == null)
            return "";

        return "Ajoût effectué";
    }

    @PostMapping("/create")
    public String create(@RequestBody Grade grade)
    {
		Grade grade2 = gradeServ.addGrade(grade);

        if(grade2 == null)
            return "";

        return "Ajoût effectué";
    }

    @GetMapping("/read/id/{gradeId}")
    public Grade read(@PathVariable Integer gradeId)
    {
        return gradeServ.findGrade(gradeId);
    }

    @GetMapping("/read/name/{gradeName}")
    public List<Grade> read(@PathVariable String gradeName)
    {
        return gradeServ.findGrade(gradeName);
    }

    @GetMapping("/read")
    public List<Grade> read()
    {
        return gradeServ.findAllGrade();
    }

    @PutMapping("/update/{gradeId}/{gradeName}/{inventory}")
    public String update(@PathVariable Integer gradeId, @PathVariable String gradeName, @PathVariable Integer inventory)
    {
        Grade grade = gradeServ.modifyGrade(gradeId, gradeName, inventory);

        if(grade == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{gradeId}")
    public Grade delete(@PathVariable Integer gradeId)
    {
        Grade grade = gradeServ.deleteGrade(gradeId);

        return grade;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        gradeServ.deleteAllGrade();

        return "Table vidée";
    }
}
