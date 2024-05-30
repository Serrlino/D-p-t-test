package com.bd_project.organisation_manage.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

import com.bd_project.organisation_manage.model.SectionIdClass;
import com.bd_project.organisation_manage.repository.SectionRepository;
import com.bd_project.organisation_manage.model.Section;

@Service
@AllArgsConstructor
public class SectionServiceImplementation implements SectionService{

	private SectionRepository sectionrepository;

    @Override
	public Section createSection(String sectionName)
    {
        Section section = new Section();

		section.setSectionName(sectionName);

        return sectionrepository.save(section);
    }

    @Override
	public Section createSection(Section section) {
		// TODO Auto-generated method stub
		return sectionrepository.save(section);
	}

    @Override
	public List<Section> indexSection() {
		// TODO Auto-generated method stub
		return sectionrepository.findAll();
	}
	
	@Override
    public Section findSection(Integer sectionId)
    {
        Section section = sectionrepository.findBySectionId(sectionId);

        return section;
    }

	@Override
    public Section deleteSection(Integer sectionId)
    {
        Section section = sectionrepository.findBySectionId(sectionId);

        sectionrepository.deleteById(sectionId);

        return section;
    }

    @Override
    public void deleteAllSection()
    {
        sectionrepository.deleteAll();
    }
}
