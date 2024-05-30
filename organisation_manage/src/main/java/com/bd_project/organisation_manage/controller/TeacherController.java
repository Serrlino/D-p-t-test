package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.model.Fonction;
import com.bd_project.organisation_manage.model.Office;
import com.bd_project.organisation_manage.service.TeacherService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherServ;

	@PostMapping(path="/creer")
	public String createTeacher(@RequestBody Teacher teacher) {
		Teacher teacher2 = teacherServ.createTeacher(teacher);

        if(teacher2 == null)
            return "";

		return "Ajoût effectué";
	}

    @PostMapping("/create/{teacherName}/{teacherAddress}/{teacherBirthDate}/{teacherSex}/{teacherContact}/{officeId}/{fonctionName}")
    public String create(@PathVariable String teacherName, @PathVariable String teacherAddress, @PathVariable String teacherBirthDate,
    @PathVariable String teacherSex, @PathVariable Integer teacherContact, @PathVariable Integer officeId, @PathVariable String fonctionName)
    {
        Teacher teacher = teacherServ.createTeacher(teacherName, teacherAddress,
        teacherBirthDate, teacherSex, teacherContact, officeId, fonctionName);

        if(teacher == null)
            return "";

		return "Ajoût effectué";
    }

    @PostMapping("/create/{teacherName}/{teacherAddress}/{teacherBirthDate}/{teacherSex}/{teacherContact}")
    public String create(@PathVariable String teacherName, @PathVariable String teacherAddress, @PathVariable String teacherBirthDate,
    @PathVariable String teacherSex, @PathVariable Integer teacherContact, @RequestBody Office office, @RequestBody Fonction fonction)
    {
        Teacher teacher = teacherServ.createTeacher(teacherName, teacherAddress,
        teacherBirthDate,teacherSex, teacherContact, office, fonction);

        if(teacher == null)
            return "";

		return "Ajoût effectué";
    }

    @GetMapping("/read/{registerNumber}")
    public Teacher read(@PathVariable Integer registerNumber)
    {
        return teacherServ.findTeacher(registerNumber);
    }

    @GetMapping(path="/index")
	public List<Teacher> indexTeacher(){
		return teacherServ.indexTeacher();
	}

    @PutMapping("/update/{registerNumber}/{teacherName}/{teacherAddress}/{teacherBirthDate}/{teacherSex}/{teacherContact}/{officeId}/{fonctionName}")
    public String update(@PathVariable Integer registerNumber, @PathVariable String teacherName, @PathVariable String teacherAddress, @PathVariable String teacherBirthDate, @PathVariable String teacherSex, @PathVariable Integer teacherContact, @PathVariable Integer officeId, @PathVariable String fonctionName)
    {
        Teacher teacher = teacherServ.modifyTeacher(registerNumber, teacherName,
        teacherAddress, teacherBirthDate, teacherSex, teacherContact, officeId, fonctionName);

        if(teacher == null)
            return "";

        return "Mise à jour effectuée";
    }

    @PutMapping("/update/{registerNumber}/{teacherName}/{teacherAddress}/{teacherBirthDate}/{teacherSex}/{teacherContact}")
    public String update(@PathVariable Integer registerNumber, @PathVariable String teacherName, @PathVariable String teacherAddress, @PathVariable String teacherBirthDate, @PathVariable String teacherSex, @PathVariable Integer teacherContact, @RequestBody Office office, @RequestBody Fonction fonction)
    {
        Teacher teacher = teacherServ.modifyTeacher(registerNumber, teacherName,
        teacherAddress, teacherBirthDate, teacherSex, teacherContact, office, fonction);

        if(teacher == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{registerNumber}")
    public Teacher delete(@PathVariable Integer registerNumber)
    {
        Teacher teacher = teacherServ.deleteTeacher(registerNumber);

        return teacher;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        teacherServ.deleteAllTeacher();

        return "Table vidée";
    }
}
