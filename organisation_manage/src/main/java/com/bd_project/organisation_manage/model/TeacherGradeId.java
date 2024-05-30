package com.bd_project.organisation_manage.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherGradeId implements Serializable{
    private Teacher gradeTeacher;
    private Grade teacherGrade;
}
