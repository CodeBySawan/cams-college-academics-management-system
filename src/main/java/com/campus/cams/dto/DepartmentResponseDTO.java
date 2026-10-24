
package com.campus.cams.dto;

import com.campus.cams.entity.DepartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDTO {

    private Long id;
    private String departmentCode;
    private String departmentName;
    private String departmentDescription;
    private DepartmentStatus departmentStatus;
}
