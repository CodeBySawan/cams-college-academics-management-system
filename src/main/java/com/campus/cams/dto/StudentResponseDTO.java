
package com.campus.cams.dto;

import com.campus.cams.entity.StudentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String rollNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer admissionYear;
    private StudentStatus status;
}
