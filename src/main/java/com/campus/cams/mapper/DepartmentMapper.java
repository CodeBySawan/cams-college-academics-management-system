package com.campus.cams.mapper;

import com.campus.cams.dto.DepartmentRequestDTO;
import com.campus.cams.dto.DepartmentResponseDTO;
import com.campus.cams.entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequestDTO dto) {

        Department department = new Department();

        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentStatus(dto.getDepartmentStatus());

        return department;
    }

    public static DepartmentResponseDTO toResponseDTO(Department department) {

        DepartmentResponseDTO response = new DepartmentResponseDTO();

        response.setId(department.getId());
        response.setDepartmentCode(department.getDepartmentCode());
        response.setDepartmentName(department.getDepartmentName());
        response.setDepartmentDescription(department.getDepartmentDescription());
        response.setDepartmentStatus(department.getDepartmentStatus());

        return response;
    }

    public static Department updateEntity(
            DepartmentRequestDTO dto,
            Department department) {

        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDescription(dto.getDepartmentDescription());
        department.setDepartmentStatus(dto.getDepartmentStatus());

        return department;
    }
}