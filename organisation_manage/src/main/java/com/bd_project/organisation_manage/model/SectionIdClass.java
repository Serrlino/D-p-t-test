package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionIdClass  implements Serializable{
	private Integer sectionId;
	private String sectionName;
}
 