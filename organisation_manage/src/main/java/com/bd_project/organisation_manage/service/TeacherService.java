package com.bd_project.organisation_manage.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.bd_project.organisation_manage.model.Fonction;
import com.bd_project.organisation_manage.model.Teacher;
import com.bd_project.organisation_manage.repository.FonctionRepository;
import com.bd_project.organisation_manage.repository.OfficeRepository;
import com.bd_project.organisation_manage.repository.TeacherRepository;
import com.bd_project.organisation_manage.model.Office;

@Service
@AllArgsConstructor
public class TeacherService{

    private TeacherRepository teacherRep;
    private OfficeRepository officeRep;
    private FonctionRepository fonctionRep;

    public Teacher createTeacher(String teacherName, String teacherAddress, String teacherBirthDate,
    String teacherSex, Integer teacherContact, Integer officeId, String fonctionName)
    {
        if(teacherRep.findByTeacherName(teacherName) != null)
            return null;

        Office office = officeRep.findByOfficeId(officeId);
        Fonction fonction = fonctionRep.findByFonctionName(fonctionName);

        Teacher teacher = new Teacher();

        teacher.setTeacherName(teacherName);
        teacher.setTeacherAddress(teacherAddress);
        teacher.setTeacherBirthDate(teacherBirthDate);
        teacher.setTeacherSex(teacherSex);
        teacher.setTeacherContact(teacherContact);
        teacher.setTeacherOffice(office);
        teacher.setTeacherFonction(fonction);

        return teacherRep.save(teacher);
    }

    public Teacher createTeacher(String teacherName, String teacherAddress, String teacherBirthDate,
    String teacherSex, Integer teacherContact, Office office, Fonction fonction)
    {
        if(teacherRep.findByTeacherName(teacherName) != null)
            return null;

        Teacher teacher = new Teacher();

        teacher.setTeacherName(teacherName);
        teacher.setTeacherAddress(teacherAddress);
        teacher.setTeacherBirthDate(teacherBirthDate);
        teacher.setTeacherSex(teacherSex);
        teacher.setTeacherContact(teacherContact);
        teacher.setTeacherOffice(office);
        teacher.setTeacherFonction(fonction);

        return teacherRep.save(teacher);
    }

    public Teacher createTeacher(Teacher teacher)
    {
        if(teacherRep.findByTeacherName(teacher.getTeacherName()) != null)
            return null;

        return teacherRep.save(teacher);
    }

    public Teacher findTeacher(Integer registerNumber)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);

        return teacher;
    }

    public Teacher findTeacher(String teacherName)
    {
        Teacher teacher = teacherRep.findByTeacherName(teacherName);

        return teacher;
    }

	public List<Teacher> indexTeacher() {
		// TODO Auto-generated method stub
		return teacherRep.findAll();
	}
  
    public Teacher modifyTeacher(Integer registerNumber, String teacherName, String teacherAddress,
    String teacherBirthDate, String teacherSex, Integer teacherContact, Integer officeId, String fonctionName)
    {
        if(teacherRep.findByTeacherName(teacherName) != null)
            return null;
        
        Office office = officeRep.findByOfficeId(officeId);
        Fonction fonction = fonctionRep.findByFonctionName(fonctionName);

        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);

        teacher.setTeacherName(teacherName);
        teacher.setTeacherAddress(teacherAddress);
        teacher.setTeacherBirthDate(teacherBirthDate);
        teacher.setTeacherSex(teacherSex);
        teacher.setTeacherContact(teacherContact);
        teacher.setTeacherOffice(office);
        teacher.setTeacherFonction(fonction);

        return teacherRep.save(teacher);
    }

    public Teacher modifyTeacher(Integer registerNumber, String teacherName, String teacherAddress,
    String teacherBirthDate, String teacherSex, Integer teacherContact, Office office, Fonction fonction)
    {
        if(teacherRep.findByTeacherName(teacherName) != null)
            return null;

        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);

        teacher.setTeacherName(teacherName);
        teacher.setTeacherAddress(teacherAddress);
        teacher.setTeacherBirthDate(teacherBirthDate);
        teacher.setTeacherSex(teacherSex);
        teacher.setTeacherContact(teacherContact);
        teacher.setTeacherOffice(office);
        teacher.setTeacherFonction(fonction);

        return teacherRep.save(teacher);
    }

    public Teacher deleteTeacher(Integer registerNumber)
    {
        Teacher teacher = teacherRep.findByRegisterNumber(registerNumber);
        teacherRep.deleteById(registerNumber);

        return teacher;
    }

    public void deleteAllTeacher()
    {
        teacherRep.deleteAll();
    }

}