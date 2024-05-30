package com.bd_project.organisation_manage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bd_project.organisation_manage.repository.GradeRepository;
import com.bd_project.organisation_manage.model.Grade;

@Service
@AllArgsConstructor
public class GradeService{

    private GradeRepository gradeRep;

    public Grade addGrade(String gradeName, Integer inventory)
    {
        Grade grade = new Grade();

        grade.setGradeName(gradeName);
        grade.setInventory(inventory);

        return gradeRep.save(grade);
    }

    public Grade addGrade(Grade grade)
    {
        return gradeRep.save(grade);
    }


    public Grade findGrade(Integer gradeId)
    {
        Grade grade = gradeRep.findByGradeId(gradeId);

        return grade;
    }

    public List<Grade> findGrade(String gradeName)
    {
        List<Grade> grade = gradeRep.findByGradeName(gradeName);

        return grade;
    }

    public List<Grade> findAllGrade()
    {
        List<Grade> grades = gradeRep.findAll();

        return grades;
    }
  
    public Grade modifyGrade(Integer gradeId, String gradeName, Integer inventory)
    {
        Grade grade = gradeRep.findByGradeId(gradeId);

        grade.setGradeName(gradeName);
        grade.setInventory(inventory);

        return gradeRep.save(grade);
    }

    public Grade deleteGrade(Integer gradeId)
    {
        Grade grade = gradeRep.findByGradeId(gradeId);
        gradeRep.deleteById(gradeId);

        return grade;
    }

    public void deleteAllGrade()
    {
        gradeRep.deleteAll();
    }

}
