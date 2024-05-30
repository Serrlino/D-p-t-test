package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Grade;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>{

    Grade findByGradeId(Integer gradeId);
    List<Grade> findByGradeName(String gradeName); 
}
