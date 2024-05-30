package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Material {
    @Id
    // @GeneratedValue
    @Column(length = 32, nullable = false)
    private String code;

    @Column(length = 32, nullable = false)
    private String materialName;

    @Column(nullable = false)
    private Integer materialCoef;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade materialGrade;

    @ManyToOne
    @JoinColumn(name = "register_number")
    private Teacher materialTeacher;
}
