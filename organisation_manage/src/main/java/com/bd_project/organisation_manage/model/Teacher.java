package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Column; 
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 4, nullable = false)
    private Integer registerNumber;

    @Column(length = 32, nullable = false)
	private String teacherName;

    @Column(length = 32, nullable = false)
	private String teacherAddress;

    @Column(length = 20, nullable = false)
	private String teacherBirthDate;

    @Column(length = 15, nullable = false)
	private String teacherSex;

    @Column(length = 10, nullable = false)
	private Integer teacherContact;

    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office teacherOffice;

    @ManyToOne
    @JoinColumn(name = "fonction_name", nullable = false)
    private Fonction teacherFonction;

    @JsonIgnore
    @OneToMany(mappedBy = "materialTeacher", cascade = CascadeType.ALL)
    private List<Material> materials;
    
    @JsonIgnore
    @OneToMany(mappedBy = "gradeTeacher", cascade = CascadeType.ALL)
    private List<Assign> grades;
}
