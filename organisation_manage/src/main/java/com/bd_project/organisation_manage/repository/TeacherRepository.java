package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.model.Fonction;
import com.bd_project.organisation_manage.model.Office;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

    Teacher findByRegisterNumber(Integer registerNumber);    
    Teacher findByTeacherName(String teacherName);    
    List<Teacher> findByTeacherBirthDate(String teacherBirthDate);    
    List<Teacher> findByTeacherAddress(String teacherAddress);    
    List<Teacher> findByTeacherSex(String teacherSex);    
    List<Teacher> findByTeacherFonction(Fonction teacherFonction);    
    List<Teacher> findByTeacherOffice(Office teacherOffice);    
}
