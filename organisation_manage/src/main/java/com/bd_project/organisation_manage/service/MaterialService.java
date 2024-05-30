package com.bd_project.organisation_manage.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

import com.bd_project.organisation_manage.model.Material;
import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.repository.GradeRepository;
import com.bd_project.organisation_manage.repository.MaterialRepository;
import com.bd_project.organisation_manage.repository.TeacherRepository;
import com.bd_project.organisation_manage.model.Grade;

@Service
@AllArgsConstructor
public class MaterialService{

    private MaterialRepository materialRep;
    private TeacherRepository teacherRep;
    private GradeRepository gradeRep;

    public Material addMaterial(String code, String materialName, Integer materialCoef, Integer gradeId, Integer registerNumber)
    {
        Grade grade = gradeRep.findByGradeId(gradeId);
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);

        Material material = new Material(code, materialName, materialCoef, grade, teacher);

        return materialRep.save(material);
    }

    public Material addMaterial(Material material)
    {
        return materialRep.save(material);
    }

    public Material findMaterial(String code)
    {
        Material material = materialRep.findByCode(code);

        return material;
    }

    // public List<Material> findMaterial(String materialName)Material

    public List<Material> findMaterial(Integer materialCoef)
    {
        List<Material> materials = materialRep.findByMaterialCoef(materialCoef);

        return materials;
    }

    public List<Material> findAllMaterial()
    {
        List<Material> materials = materialRep.findAll();

        return materials;
    }
  
    public Material modifyMaterial(String code, String materialName, Integer materialCoef, Integer gradeId, Integer registerNumber)
    {
        Grade grade = gradeRep.findByGradeId(gradeId);
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);

        Material material = materialRep.findByCode(code);

        material.setCode(code);
        material.setMaterialName(materialName);
        material.setMaterialCoef(materialCoef);
        material.setMaterialGrade(grade);
        material.setMaterialTeacher(teacher);

        return materialRep.save(material);
    }

    public Material deleteMaterial(String code)
    {
        Material material = materialRep.findByCode(code);
        materialRep.deleteById(code);

        return material;
    }

    public void deleteAllMaterial()
    {
        materialRep.deleteAll();
    }

}