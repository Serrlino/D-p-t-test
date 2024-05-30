package com.bd_project.organisation_manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bd_project.organisation_manage.model.Series;
import com.bd_project.organisation_manage.service.SeriesService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bd_project.organisation_manage.repository.SeriesRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/series")
public class SeriesController {
	
	@Autowired
	private SeriesService seriesservice;
	
	@PostMapping(path="/creer")
	public String createSeries(@RequestBody Series series) {

        Series series2 = seriesservice.createSeries(series);

        if(series2 == null)
            return "";

		return "Ajout d'une série réussi !";
	}
	
    @PostMapping("/create/{seriesName}")
    public String create(@PathVariable String seriesName)
    {
        Series series = seriesservice.createSeries(seriesName);

        if(series == null)
            return "";

        return "Ajoût effectué";
    }
	
	@GetMapping(path="/index")
	public List<Series> indexSeries(){
		return seriesservice.indexSeries();
	}

    @GetMapping("/read/{code}")
    public Series read(@PathVariable Integer code)
    {
        return seriesservice.findSeries(code);
    }

    @PutMapping("/update/{code}/{seriesName}")
    public String update(@PathVariable Integer code, @PathVariable String seriesName)
    {
        Series series = seriesservice.modifySeries(code, seriesName);

        if(series == null)
            return "";

        return "Mise à jour effectuée";
    }

    @DeleteMapping("/delete/{code}")
    public Series delete(@PathVariable Integer code)
    {
        Series series = seriesservice.deleteSeries(code);

        return series;
    }

    @DeleteMapping("/delete")
    public String delete()
    {
        seriesservice.deleteAllSeries();

        return "Table vidée";
    }
}
