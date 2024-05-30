package com.bd_project.organisation_manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Associate;
import com.bd_project.organisation_manage.service.AssociateService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/associate")
public class AssociateController {
	
	@Autowired
	private AssociateService associateservice;
	
	@PostMapping(path="/creer")
	public String createAssociate(@RequestBody Associate associate) {
        Associate associate2 = associateservice.createAssociate(associate);

        if(associate2 == null)
            return "";

		return "Ajout d'une association grades<>series réussi !";
	}
	
	@GetMapping(path="/index")
	public List<Associate> indexAssociate(){
		return associateservice.indexAssociate();
	}

	
    @PostMapping("create/{gradeId}/{code}")
    public String create(@PathVariable Integer gradeId, @PathVariable Integer code)
    {
        Associate associate = associateservice.createAssociate(gradeId, code);

        if(associate == null)
            return "";

        return "Ajoût effectué";
    }

    @GetMapping("read/{gradeId}/{code}")
    public Associate read(@PathVariable Integer gradeId, @PathVariable Integer code)
    {
        return associateservice.findAssociate(gradeId, code);
    }

    @DeleteMapping("delete/{gradeId}/{code}")
    public Associate delete(@PathVariable Integer gradeId, @PathVariable Integer code)
    {
        Associate associate = associateservice.deleteAssociate(gradeId, code);

        return associate;
    }

    @DeleteMapping("delete")
    public String delete()
    {
        associateservice.deleteAllAssociate();

        return "Table vidée";
    }
}