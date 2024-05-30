package com.bd_project.organisation_manage.service;

import java.util.List;

import com.bd_project.organisation_manage.model.Associate;
import com.bd_project.organisation_manage.model.SeriesGradeId;

public interface AssociateService {
	public Associate createAssociate(Associate associate);
	public Associate createAssociate(Integer gradeId, Integer code);
	public List<Associate> indexAssociate();
	public Associate findAssociate(Integer gradeId, Integer code);
	public Associate findAssociate(SeriesGradeId seriesGradeId);
	public Associate deleteAssociate(Integer gradeId, Integer code);
	public Associate deleteAssociate(SeriesGradeId seriesGradeId);
	public void deleteAllAssociate();
}
