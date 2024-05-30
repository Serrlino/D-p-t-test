package com.bd_project.organisation_manage.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Cycle;
import com.bd_project.organisation_manage.service.CycleService;

import org.springframework.web.bind.annotation.PathVariable;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/cycle")
@AllArgsConstructor
public class CycleController {
	
	private CycleService cycleservice;
		
	// @PostMapping("/creer")
	// public String createCycle(@RequestBody Cycle cycle) {
	// 	Cycle cycle2 = cycleservice.createCycle(cycle);

    //     if(cycle2 == null)
    //         return "";

	// 	return "Ajout d'une nouvelle cycle reussi !";
	// }
	
	@PostMapping("/create/{gradeId}/{sectionId}")
	public String create(@PathVariable Integer gradeId, @PathVariable Integer sectionId)
    {
		Cycle cycle = cycleservice.createCycle(gradeId, sectionId);

        if(cycle == null)
            return "";

		return "Ajoût effectué";
    }
	
	@GetMapping("/index")
	public List<Cycle> indexCycle(){
		return cycleservice.indexCycle();
	}

	@GetMapping("/read/{cycleId}")
    public Cycle read(@PathVariable Integer cycleId)
    {
        Cycle cycle = cycleservice.findCycle(cycleId);

        return cycle;
    }

	@PutMapping("/update/{cycleId}/{gradeId}/{sectionId}")
    public String update(@PathVariable Integer cycleId, @PathVariable Integer gradeId, @PathVariable Integer sectionId)
    {
		Cycle cycle = cycleservice.modifyCycle(cycleId, gradeId, sectionId);

        if(cycle == null)
            return "";

        cycleservice.modifyCycle(cycleId, gradeId, sectionId);

        return "Mise à jour effectuée";
    }


	@DeleteMapping("/delete/{cycleId}")
    public Cycle delete(@PathVariable Integer cycleId)
    {
        Cycle cycle = cycleservice.deleteCycle(cycleId);

        return cycle;
    }

	@DeleteMapping("/delete")
    public String delete()
    {
        cycleservice.deleteAllCycle();

		return "Table vidée";
    }
}
