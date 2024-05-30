package com.bd_project.organisation_manage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.bd_project.organisation_manage.model.Fonction;
import com.bd_project.organisation_manage.repository.FonctionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FonctionService{

    private FonctionRepository fonctionRep;

    public Fonction addFonction(String fonctionName, String description)
    {
        if(fonctionRep.findByFonctionName(fonctionName) != null)
            return null;

        Fonction fonction = new Fonction();

        fonction.setFonctionName(fonctionName);
        fonction.setDescription(description);

        return fonctionRep.save(fonction);
    }

    public Fonction addFonction(Fonction fonction)
    {
        if(fonctionRep.findByFonctionName(fonction.getFonctionName()) != null)
            return null;

        return fonctionRep.save(fonction);
    }


    public Fonction findFonction(String fonctionName)
    {
        Fonction fonction = fonctionRep.findByFonctionName(fonctionName);

        return fonction;
    }

    public List<Fonction> findAllFonction()
    {
        List<Fonction> fonctions = fonctionRep.findAll();

        return fonctions;
    }
  
    public Fonction modifyFonction(String fonctionName, String description)
    {
        if(fonctionRep.findByFonctionName(fonctionName) != null)
            return null;

        Fonction fonction = fonctionRep.findByFonctionName(fonctionName);

        fonction.setDescription(description);

        return fonctionRep.save(fonction);
    }

    public Fonction deleteFonction(String fonctionName)
    {
        Fonction fonction = fonctionRep.findByFonctionName(fonctionName);
        fonctionRep.deleteById(fonctionName);

        return fonction;
    }

    public void deleteAllFonction()
    {
        fonctionRep.deleteAll();
    }

}
