package com.campus.cams.controller;

import com.campus.cams.dto.DepartmentRequestDTO;
import com.campus.cams.dto.DepartmentResponseDTO;
import com.campus.cams.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public DepartmentResponseDTO createDepartment(@Valid @RequestBody DepartmentRequestDTO dto) {
        return departmentService.createDepartment(dto);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
        return departmentService.getAllDepartments(pageable);
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id, @Valid @RequestBody DepartmentRequestDTO dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<DepartmentResponseDTO> searchDepartments(
            @RequestParam String keyword) {
        return departmentService.searchDepartments(keyword);
    }
}