package com.campus.cams.repository;

import com.campus.cams.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByRollNumber(String rollNumber);

    boolean existsByEmail(String email);

    boolean existsByRollNumberAndIdNot(String rollNumber, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Student> findByFirstNameContainingIgnoreCase(String firstName);

    List<Student> findByRollNumberContainingIgnoreCase(String rollNumber);

    List<Student> findByEmailContainingIgnoreCase(String email);

}
