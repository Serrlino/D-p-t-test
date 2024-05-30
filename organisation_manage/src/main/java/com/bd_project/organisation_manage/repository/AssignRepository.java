package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Assign;
import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.model.Grade;
import com.bd_project.organisation_manage.model.TeacherGradeId;
import java.util.List;

@Repository
public interface AssignRepository extends JpaRepository<Assign, TeacherGradeId>{
    Assign findByGradeTeacherAndTeacherGrade(Teacher gradeTeacher, Grade teacherGrade);
    List<Assign> findByGradeTeacher(Teacher gradeTeacher);
    List<Assign> findByTeacherGrade(Grade teacherGrade);
    List<Assign> findByCopyNumber(String copyNumber);
    List<Assign> findByTeacherMain(Boolean teacherMain);
}
