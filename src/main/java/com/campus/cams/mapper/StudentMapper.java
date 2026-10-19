package com.campus.cams.mapper;

import com.campus.cams.dto.StudentRequestDTO;
import com.campus.cams.dto.StudentResponseDTO;
import com.campus.cams.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto) {

        Student student = new Student();

        student.setRollNumber(dto.getRollNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setStatus(dto.getStatus());

        return student;
    }


    public static StudentResponseDTO toResponseDTO(Student student) {

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(student.getId());
        response.setRollNumber(student.getRollNumber());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        response.setPhoneNumber(student.getPhoneNumber());
        response.setAdmissionYear(student.getAdmissionYear());
        response.setStatus(student.getStatus());

        return response;
    }

    public static Student updateEntity(StudentRequestDTO dto, Student student) {
        student.setRollNumber(dto.getRollNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setStatus(dto.getStatus());

        return student;
    }
}