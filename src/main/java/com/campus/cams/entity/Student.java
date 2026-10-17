package com.campus.cams.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_student_roll_number", columnNames = "roll_number"),
                @UniqueConstraint(name = "uk_student_email", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Roll number is required")
    @Size(max = 50, message = "Roll number cannot exceed 50 characters")
    @Column(name = "roll_number", nullable = false, length = 50)
    private String rollNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    @Column(nullable = false, length = 150)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @NotNull(message = "Admission year is required")
    @Min(value = 2000, message = "Admission year cannot be before 2000")
    @Max(value = 2100, message = "Admission year cannot be after 2100")
    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear;

    // Kept as String for now; can be swapped for an enum later
    @NotBlank(message = "Status is required")
    @Size(max = 30, message = "Status cannot exceed 30 characters")
    @Column(nullable = false, length = 30)
    private String status;
}

