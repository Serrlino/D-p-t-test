package com.bd_project.organisation_manage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bd_project.organisation_manage.model.Assign;
import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.repository.AssignRepository;
import com.bd_project.organisation_manage.repository.GradeRepository;
import com.bd_project.organisation_manage.repository.TeacherRepository;
import com.bd_project.organisation_manage.model.Grade;
import com.bd_project.organisation_manage.model.TeacherGradeId;

@Service
@AllArgsConstructor
public class AssignService{
    private AssignRepository assignRep;
    private TeacherRepository teacherRep;
    private GradeRepository gradeRep;

    public Assign addAssign(Integer registerNumber, Integer gradeId, String copyNumber, Boolean teacherMain)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Assign assign = new Assign(teacher, grade, copyNumber, teacherMain);

        return assignRep.save(assign);
    }

    public Assign addAssign(Assign assign)
    {
        return assignRep.save(assign);
    }

    public Assign findAssign(Integer registerNumber, Integer gradeId)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Assign assign = assignRep.findByGradeTeacherAndTeacherGrade(teacher, grade);

        return assign;
    }

    public Assign findAssign(TeacherGradeId teacherGradeId)
    {
        Teacher teacher = teacherGradeId.getGradeTeacher();
        Grade grade = teacherGradeId.getTeacherGrade();

        Assign assign = assignRep.findByGradeTeacherAndTeacherGrade(teacher, grade);

        return assign;
    }

    public List<Assign> findAllAssign()
    {
        List<Assign> assigns = assignRep.findAll();

        return assigns;
    }
  
    public Assign modifyAssign(Integer registerNumber, Integer gradeId, String copyNumber, Boolean teacherMain)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Assign assign = assignRep.findByGradeTeacherAndTeacherGrade(teacher, grade);

        assign.setCopyNumber(copyNumber);
        assign.setTeacherMain(teacherMain);

        return assignRep.save(assign);
    }

    public Assign deleteAssign(Integer registerNumber, Integer gradeId)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Assign assign = assignRep.findByGradeTeacherAndTeacherGrade(teacher, grade);

        TeacherGradeId teacherGradeId = new TeacherGradeId(teacher, grade);

        assignRep.deleteById(teacherGradeId);

        return assign;
    }

    public Assign deleteAssign(TeacherGradeId teacherGradeId)
    {
        Teacher teacher = teacherGradeId.getGradeTeacher();
        Grade grade = teacherGradeId.getTeacherGrade();

        Assign assign = assignRep.findByGradeTeacherAndTeacherGrade(teacher, grade);

        assignRep.deleteById(teacherGradeId);

        return assign;
    }

    public void deleteAllAssign()
    {
        assignRep.deleteAll();
    }
}
