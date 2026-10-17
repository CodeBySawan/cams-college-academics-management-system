package com.campus.cams.service;

import com.campus.cams.entity.Student;
import com.campus.cams.exception.DuplicateStudentException;
import com.campus.cams.exception.StudentNotFoundException;
import com.campus.cams.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //create Student
    public Student createStudent(Student student) {

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
        return studentRepository.save(student);
    }

    //update Student
    public Student updateStudent(Long id, Student updatedStudent) {

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

        existingStudent.setRollNumber(updatedStudent.getRollNumber());
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
        existingStudent.setAdmissionYear(updatedStudent.getAdmissionYear());
        existingStudent.setStatus(updatedStudent.getStatus());

        return studentRepository.save(existingStudent);
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));
        studentRepository.deleteById(id);
    }
}
