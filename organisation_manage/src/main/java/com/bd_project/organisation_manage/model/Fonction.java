package com.bd_project.organisation_manage.model;

import java.util.List;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "mlr1_schema")
public class Fonction {
    @Id
    // @GeneratedValue
    @Column(length = 32, nullable = false)
    private String fonctionName;

    @Column(length = 255, nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "teacherFonction", cascade = CascadeType.ALL)
    private List<Teacher> teachers;
}
