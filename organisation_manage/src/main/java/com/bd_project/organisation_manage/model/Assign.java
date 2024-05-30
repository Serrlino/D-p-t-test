package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column; 
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TeacherGradeId.class)
@Table(schema = "mlr1_schema")
public class Assign {
    @Id
    @ManyToOne
    @JoinColumn(name = "register_number")
    private Teacher gradeTeacher;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade teacherGrade;

    @Column(length = 32)
    private String copyNumber;

    @Column
    private Boolean teacherMain;
}
