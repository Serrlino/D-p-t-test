package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Integer> {

    Series findByCode(Integer code);
    Series findBySeriesName(String SeriesName);
}
