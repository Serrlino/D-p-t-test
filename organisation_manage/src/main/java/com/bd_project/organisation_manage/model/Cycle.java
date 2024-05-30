package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Cycle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cycleId;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "grade_id")
	private Grade cycleGrade;

	@ManyToOne
	@JsonIgnore
	@JoinColumns({
		@JoinColumn(name = "section_id", referencedColumnName = "sectionId"),
		@JoinColumn(name = "section_name", referencedColumnName = "sectionName")
	})
	private Section cycleSection;

	public Cycle() {
		super();
	}

	public int getCycleId() {
		return cycleId;
	}

	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}
}
