package com.bd_project.organisation_manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bd_project.organisation_manage.model.Associate;
import com.bd_project.organisation_manage.model.Series;
import com.bd_project.organisation_manage.model.SeriesGradeId;
import com.bd_project.organisation_manage.repository.AssociateRepository;
import com.bd_project.organisation_manage.repository.GradeRepository;
import com.bd_project.organisation_manage.repository.SeriesRepository;
import com.bd_project.organisation_manage.model.Grade;


@Service
public class AssociateServiceImplementation implements AssociateService {
	
	@Autowired
	private AssociateRepository associaterepository;

	@Autowired
    private SeriesRepository seriesRep;

	@Autowired
    private GradeRepository gradeRep;

	
	@Override
	public Associate createAssociate(Associate associate) {
		// TODO Auto-generated method stub
		return associaterepository.save(associate);
	}
	
	@Override
	public List<Associate> indexAssociate() {
		// TODO Auto-generated method stub
		return associaterepository.findAll();
	}

	@Override
    public Associate createAssociate(Integer gradeId, Integer code)
    {
        Series series = seriesRep.findByCode(code);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Associate associate = new Associate(grade, series);

        return associaterepository.save(associate);
    }

	@Override
    public Associate findAssociate(Integer gradeId, Integer code)
    {
        Series series = seriesRep.findByCode(code);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Associate associate = associaterepository.findByGradeAssoAndSeriesAsso(grade, series);

        return associate;
    }

	@Override
    public Associate findAssociate(SeriesGradeId seriesGradeId)
    {
        Grade grade = seriesGradeId.getGradeAsso();
        Series series = seriesGradeId.getSeriesAsso();

        Associate associate = associaterepository.findByGradeAssoAndSeriesAsso(grade, series);

        return associate;
    }
  
	@Override
    public Associate deleteAssociate(Integer gradeId, Integer code)
    {
        Series series = seriesRep.findByCode(code);
        Grade grade = gradeRep.findByGradeId(gradeId);

        Associate associate = associaterepository.findByGradeAssoAndSeriesAsso(grade, series);

        SeriesGradeId seriesGradeId = new SeriesGradeId(grade, series);

        associaterepository.deleteById(seriesGradeId);

        return associate;
    }

	@Override
    public Associate deleteAssociate(SeriesGradeId seriesGradeId)
    {
        Grade grade = seriesGradeId.getGradeAsso();
        Series series = seriesGradeId.getSeriesAsso();

        Associate associate = associaterepository.findByGradeAssoAndSeriesAsso(grade, series);

        associaterepository.deleteById(seriesGradeId);

        return associate;
    }

	@Override
    public void deleteAllAssociate()
    {
        associaterepository.deleteAll();
    }
}