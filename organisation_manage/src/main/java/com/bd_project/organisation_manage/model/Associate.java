package com.bd_project.organisation_manage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@IdClass(SeriesGradeId.class)
@Table(schema = "mlr1_schema")

public class Associate {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "grade_id")
	private Grade gradeAsso;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "code")
	private Series seriesAsso;

	public Associate() {
		super();
	}

	public Grade getGradeAsso() {
		return gradeAsso;
	}

	public void setGradeAsso(Grade gradeAsso) {
		this.gradeAsso = gradeAsso;
	}

	public Series getSeriesAsso() {
		return seriesAsso;
	}

	public void setSeriesAsso(Series seriesAsso) {
		this.seriesAsso = seriesAsso;
	}
	
	
}
