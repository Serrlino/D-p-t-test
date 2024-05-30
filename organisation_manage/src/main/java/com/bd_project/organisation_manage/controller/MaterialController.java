package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Material;
import com.bd_project.organisation_manage.service.MaterialService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/matierial")
@CrossOrigin("*")
@AllArgsConstructor
public class MaterialController {
    private MaterialService materialServ;

    @PostMapping("/create/{code}/{materialName}/{materialCoef}/{gradeId}/{registerNumber}")
    public String create(@PathVariable String code, @PathVariable String materialName,
    @PathVariable Integer materialCoef, @PathVariable Integer gradeId, @PathVariable Integer registerNumber)
    {
		Material material = materialServ.addMaterial(code, materialName, materialCoef, gradeId, registerNumber);

        if(material == null)
            return "";

        return "Ajoût effectué";
    }

    @PostMapping("/create")
    public String create(@RequestBody Material material)
    {
		Material material2 = materialServ.addMaterial(material);

        if(material2 == null)
            return "";

        materialServ.addMaterial(material);

        return "Ajoût effectué";
    }

    @GetMapping("/read/code/{code}")
    public Material read(@PathVariable String code)
    {
        return materialServ.findMaterial(code);
    }

    // @GetMapping("/read/name/{materialName}")
    // public List<Material> read(@PathVariable String materialName)
    // {
    //     return materialServ.findMaterial(materialName);
    // }

    @GetMapping("/read/coef/{materialCoef}")
    public List<Material> read(@PathVariable Integer materialCoef)
    {
        return materialServ.findMaterial(materialCoef);
    }

    @GetMapping("/read")
    public List<Material> read()
    {
        return materialServ.findAllMaterial();
    }

    @PutMapping("/update/{code}/{materialName}/{materialCoef}")
    public String update(@PathVariable String code, @PathVariable String materialName,
    @PathVariable Integer materialCoef, @PathVariable Integer gradeId, @PathVariable Integer registerNumber)
    {
		Material material = materialServ.modifyMaterial(code, materialName, materialCoef, gradeId, registerNumber);

        if(material == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{code}/")
    public Material delete(@PathVariable String code)
    {
        Material material = materialServ.deleteMaterial(code);

        return material;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        materialServ.deleteAllMaterial();

        return "Table vidée";
    }
}