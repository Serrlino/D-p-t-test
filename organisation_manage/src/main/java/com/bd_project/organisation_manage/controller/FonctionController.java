package com.bd_project.organisation_manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Fonction;
import com.bd_project.organisation_manage.service.FonctionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/fonction")
@AllArgsConstructor
public class FonctionController {
    private FonctionService fonctionServ;

    @PostMapping("/create/{fonctionName}/{description}")
    public String create(@PathVariable String fonctionName, @PathVariable String description)
    {
        Fonction fonction = fonctionServ.addFonction(fonctionName, description);

        if(fonction == null)
            return "";

        return "Ajoût effectué";
    }

    @PostMapping("/create")
    public String create(@RequestBody Fonction fonction)
    {
        Fonction fonction2 = fonctionServ.addFonction(fonction);

        if(fonction2 == null)
            return "";

        return "Ajoût effectué";
    }

    @GetMapping("/read/{fonctionName}")
    public Fonction read(@PathVariable String fonctionName)
    {
        return fonctionServ.findFonction(fonctionName);
    }

    @GetMapping("/read")
    public List<Fonction> read()
    {
        return fonctionServ.findAllFonction();
    }

    @PutMapping("/update/{fonctionName}/{description}")
    public String update(@PathVariable String fonctionName, @PathVariable String description)
    {
        Fonction fonction = fonctionServ.modifyFonction(fonctionName, description);

        if(fonction == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{fonctionName}")
    public Fonction delete(@PathVariable String fonctionName)
    {
        Fonction fonction = fonctionServ.deleteFonction(fonctionName);

        return fonction;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        fonctionServ.deleteAllFonction();

        return "Table vidée";
    }
}
