package com.campus.cams.service;
import com.campus.cams.dto.DepartmentRequestDTO;
import com.campus.cams.dto.DepartmentResponseDTO;
import com.campus.cams.entity.Department;
import com.campus.cams.exception.DepartmentNotFoundException;
import com.campus.cams.exception.DuplicateDepartmentException;
import com.campus.cams.mapper.DepartmentMapper;
import com.campus.cams.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {

        Department department = DepartmentMapper.toEntity(dto);

        if(departmentRepository.existsByDepartmentCode(department.getDepartmentCode())) {
            throw new DuplicateDepartmentException(
                    "Department code already exists :  " + department.getDepartmentCode()
            );
        }

        if(departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new DuplicateDepartmentException(
                    "Department name already exists :  " + department.getDepartmentName()
            );
        }

        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponseDTO(savedDepartment);

    }

    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + id
                ));

        if (departmentRepository.existsByDepartmentCodeAndIdNot(
                dto.getDepartmentCode(), id)) {
            throw new DuplicateDepartmentException(
                    "Department code already exists: "
                            + dto.getDepartmentCode()
            );
        }

        if (departmentRepository.existsByDepartmentNameAndIdNot(
                dto.getDepartmentName(), id)) {
            throw new DuplicateDepartmentException(
                    "Department name already exists: "
                            + dto.getDepartmentName()
            );
        }

        DepartmentMapper.updateEntity(dto, department);

        Department updatedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponseDTO(updatedDepartment);
    }

    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + id
                ));

        return DepartmentMapper.toResponseDTO(department);
    }

    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {

        Page<Department> departments= departmentRepository.findAll(pageable);

        List<DepartmentResponseDTO> responseList= new ArrayList<>();

        for(Department department: departments.getContent()) {
            responseList.add(DepartmentMapper.toResponseDTO(department));
        }

        return new PageImpl<>(responseList, pageable, departments.getTotalElements());

    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + id
                ));

        departmentRepository.delete(department);
    }

    public List<DepartmentResponseDTO> searchDepartments(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }

        List<Department> departments = new ArrayList<>();

        departments.addAll(
                departmentRepository.findByDepartmentNameContainingIgnoreCase(keyword)
        );

        departments.addAll(
                departmentRepository.findByDepartmentCodeContainingIgnoreCase(keyword)
        );

        Map<Long, Department> uniqueDepartments = new LinkedHashMap<>();

        for (Department department : departments) {
            uniqueDepartments.put(department.getId(), department);
        }

        List<DepartmentResponseDTO> responseList = new ArrayList<>();

        for (Department department : uniqueDepartments.values()) {
            responseList.add(DepartmentMapper.toResponseDTO(department));
        }

        return responseList;
    }


}
