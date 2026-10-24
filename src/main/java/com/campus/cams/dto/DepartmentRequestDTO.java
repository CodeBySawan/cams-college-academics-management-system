
package com.campus.cams.dto;

import com.campus.cams.entity.DepartmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentRequestDTO {

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
    private String departmentCode;

    @NotBlank(message = "Department name is required")
    @Size(
            min = 2,
            max = 150,
            message = "Department name must be between 2 and 150 characters"
    )
    private String departmentName;

    @Size(
            max = 500,
            message = "Department description must not exceed 500 characters"
    )
    private String departmentDescription;

    @NotNull(message = "Department status is required")
    private DepartmentStatus departmentStatus;
}
