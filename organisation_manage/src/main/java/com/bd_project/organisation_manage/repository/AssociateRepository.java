package com.bd_project.organisation_manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bd_project.organisation_manage.model.Associate;
import com.bd_project.organisation_manage.model.Series;
import com.bd_project.organisation_manage.model.SeriesGradeId;
import com.bd_project.organisation_manage.model.Grade;
import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, SeriesGradeId> {

    List<Associate> findByGradeAsso(Grade gradeAsso);
    List<Associate> findBySeriesAsso(Series seriesAsso);
    Associate findByGradeAssoAndSeriesAsso(Grade gradeAsso, Series seriesAsso);

}
