
package com.campus.cams.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "departments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_department_code",
                        columnNames = "department_code"
                ),
                @UniqueConstraint(
                        name = "uk_department_name",
                        columnNames = "department_name"
                )
        }
)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department code is required")
    @Size(
            min = 2,
            max = 20,
            message = "Department code must be between 2 and 20 characters"
    )
    @Pattern(
            regexp = "^[A-Za-z0-9_-]+$",
            message = "Department code can only contain letters, numbers, hyphens and underscores"
    )
    @Column(name = "department_code", nullable = false, length = 20)
    private String departmentCode;

    @NotBlank(message = "Department name is required")
    @Size(
            min = 2,
            max = 150,
            message = "Department name must be between 2 and 150 characters"
    )
    @Column(name = "department_name", nullable = false, length = 150)
    private String departmentName;

    @Size(
            max = 500,
            message = "Department description must not exceed 500 characters"
    )
    @Column(name = "department_description", length = 500)
    private String departmentDescription;

    @NotNull(message = "Department status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "department_status", nullable = false, length = 20)
    private DepartmentStatus departmentStatus;
}

