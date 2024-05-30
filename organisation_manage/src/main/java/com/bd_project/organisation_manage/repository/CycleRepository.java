package com.bd_project.organisation_manage.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bd_project.organisation_manage.model.Cycle;
import com.bd_project.organisation_manage.model.Grade;
import com.bd_project.organisation_manage.model.Section;
import java.util.List;

public interface CycleRepository extends JpaRepository<Cycle, Integer> {
	Cycle findByCycleId(Integer cycleId);
	List<Cycle> findByCycleGrade(Grade cycleGrade);
	List<Cycle> findByCycleSection(Section cycleSection);
}
