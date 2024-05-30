package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.SectionIdClass;
import com.bd_project.organisation_manage.model.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
	Section findBySectionId(Integer sectionId);
    Section findBySectionName(String sectionName);
}
