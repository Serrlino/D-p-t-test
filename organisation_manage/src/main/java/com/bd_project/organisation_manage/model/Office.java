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
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 4, nullable = false)
    private Integer officeId;

    @Column(length = 32, nullable = false)
    private String officeName;

    @JsonIgnore
    @OneToMany(mappedBy = "teacherOffice", cascade = CascadeType.ALL)
    private List<Teacher> teachers;
}
