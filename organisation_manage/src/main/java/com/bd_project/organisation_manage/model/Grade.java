package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 4, nullable = false)
    private Integer gradeId;

    @Column(length = 32, nullable = false)
    private String gradeName;

    @Column(length = 4, nullable = false)
    private Integer inventory;
    
    @JsonIgnore
    @OneToMany(mappedBy = "materialGrade", cascade = CascadeType.ALL) 
    private List<Material> materials;

    @JsonIgnore
    @OneToMany(mappedBy = "teacherGrade", cascade = CascadeType.ALL)
    private List<Assign> teachers;

    @JsonIgnore
    @OneToMany(mappedBy = "cycleGrade", cascade = CascadeType.ALL)
    private List<Cycle> cycles;

    @JsonIgnore
    @OneToMany(mappedBy = "gradeAsso", cascade = CascadeType.ALL)
    private List<Associate> gradeList;
}
