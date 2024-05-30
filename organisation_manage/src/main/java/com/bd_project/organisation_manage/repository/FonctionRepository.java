package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Fonction;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, String>{

    Fonction findByFonctionName(String fonctionName);
}
