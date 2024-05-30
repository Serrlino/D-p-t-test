package com.bd_project.organisation_manage.service;

import java.util.List;

import com.bd_project.organisation_manage.model.Cycle;
import com.bd_project.organisation_manage.model.SectionIdClass;

public interface CycleService {
	public Cycle createCycle(Integer gradeId, Integer sectionId);
	// public Cycle createCycle(Cycle cycle);
	public List<Cycle> indexCycle();
	public Cycle findCycle(Integer cycleId);
    public Cycle modifyCycle(Integer cycleId, Integer gradeId, Integer sectionId);
    public Cycle deleteCycle(Integer cycleId);
    public void deleteAllCycle();

}
