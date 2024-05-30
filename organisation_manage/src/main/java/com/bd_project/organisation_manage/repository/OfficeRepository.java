package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Office;
import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer>{

    Office findByOfficeId(Integer officeId);
    List<Office> findByOfficeName(String officeName);
}