package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Series {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 4, nullable = false)
	private Integer code;

	@Column(length = 32, nullable = false)
	private String seriesName;
	
	@OneToMany(mappedBy = "seriesAsso", cascade = CascadeType.ALL)
	private List<Associate> seriesList;

	public Series() {
		super();
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	
}
