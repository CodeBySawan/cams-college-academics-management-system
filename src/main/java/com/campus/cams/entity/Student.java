package com.campus.cams.entity;

import jakarta.persistence.*;
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

    @Column(name = "roll_number", nullable = false, length = 50)
    private String rollNumber;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(name = "phone_number", length = 20,nullable = false)
    private String phoneNumber;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear;

    // Kept as String for now; can be swapped for an enum later
    @Column(nullable = false, length = 30)
    private String status;
}

