package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Material;
import com.bd_project.organisation_manage.model.Grade;
import com.bd_project.organisation_manage.model.Teacher;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String>{

    Material findByCode(String code);
    List<Material> findByMaterialName(String materialName);
    List<Material> findByMaterialCoef(Integer materialCoef);
    List<Material> findByMaterialGrade(Grade grade);
    List<Material> findByMaterialTeacher(Teacher teacher);
}
