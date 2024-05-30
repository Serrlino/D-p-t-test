package com.bd_project.organisation_manage.model;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeriesGradeId implements Serializable {
	
	private Grade gradeAsso;
	private Series seriesAsso;
}
