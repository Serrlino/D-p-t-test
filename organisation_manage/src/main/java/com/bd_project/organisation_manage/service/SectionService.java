package com.bd_project.organisation_manage.service;

import java.util.List;

import com.bd_project.organisation_manage.model.SectionIdClass;
import com.bd_project.organisation_manage.model.Section;

public interface SectionService {
	public Section createSection(String sectionName);
	public Section createSection(Section section);
	public List<Section> indexSection();
	public Section findSection(Integer sectionId);
    public Section deleteSection(Integer sectionId);
    public void deleteAllSection();
}
