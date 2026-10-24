package com.campus.cams.repository;

import com.campus.cams.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentCode(String departmentCode);

    boolean existsByDepartmentCodeAndIdNot(String departmentCode, Long id);

    boolean existsByDepartmentName(String departmentName);

    boolean existsByDepartmentNameAndIdNot(String departmentName, Long id);

    List<Department> findByDepartmentNameContainingIgnoreCase(String departmentName);

    List<Department> findByDepartmentCodeContainingIgnoreCase(String keyword);
}