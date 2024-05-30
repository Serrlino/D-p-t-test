package com.bd_project.organisation_manage.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
// @Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer sectionId;

	@Column(nullable = false)
	private String sectionName;

	@JsonIgnore
	@OneToMany(mappedBy = "cycleSection", cascade = CascadeType.ALL)
	private List<Cycle> cycles;
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}
 