package com.campus.cams.service;

import com.campus.cams.dto.StudentRequestDTO;
import com.campus.cams.dto.StudentResponseDTO;
import com.campus.cams.entity.Student;
import com.campus.cams.exception.DuplicateStudentException;
import com.campus.cams.exception.StudentNotFoundException;
import com.campus.cams.mapper.StudentMapper;
import com.campus.cams.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    // Create Student
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {

        Student student = StudentMapper.toEntity(dto);

        if (studentRepository.existsByRollNumber(student.getRollNumber())) {
            throw new DuplicateStudentException(
                    "Roll number already exists: " + student.getRollNumber()
            );
        }

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new DuplicateStudentException(
                    "Email already exists: " + student.getEmail()
            );
        }

        Student savedStudent = studentRepository.save(student);

        StudentResponseDTO response = StudentMapper.toResponseDTO(savedStudent);

        return response;
    }

    //update Student
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO updatedStudent) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        if (studentRepository.existsByRollNumberAndIdNot(
                updatedStudent.getRollNumber(), id)) {
            throw new DuplicateStudentException(
                    "Roll number already exists: " + updatedStudent.getRollNumber()
            );
        }

        if (studentRepository.existsByEmailAndIdNot(
                updatedStudent.getEmail(), id)) {
            throw new DuplicateStudentException(
                    "Email already exists: " + updatedStudent.getEmail()
            );
        }

        existingStudent=StudentMapper.toEntity(updatedStudent);

        Student s= studentRepository.save(existingStudent);

        return StudentMapper.toResponseDTO(s);
    }


    public StudentResponseDTO getStudentById(Long id) {
        Student student= studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        return StudentMapper.toResponseDTO(student);
    }


    public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(StudentMapper::toResponseDTO);
    }


    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        studentRepository.deleteById(id);
    }


    public List<StudentResponseDTO> searchStudents(String keyword) {

        List<Student> students = new ArrayList<>();

        students.addAll(
                studentRepository.findByFirstNameContainingIgnoreCase(keyword)
        );

        students.addAll(
                studentRepository.findByRollNumberContainingIgnoreCase(keyword)
        );

        students.addAll(
                studentRepository.findByEmailContainingIgnoreCase(keyword)
        );

        Map<Long, Student> uniqueStudents = new LinkedHashMap<>();

        for (Student student : students) {
            uniqueStudents.put(student.getId(), student);
        }

        List<StudentResponseDTO> responseList = new ArrayList<>();

        for (Student student : uniqueStudents.values()) {
            responseList.add(StudentMapper.toResponseDTO(student));
        }

        return responseList;
    }
}
