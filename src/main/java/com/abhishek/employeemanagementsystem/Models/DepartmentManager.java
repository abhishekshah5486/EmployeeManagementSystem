package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DepartmentManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    @ManyToOne
    private Department department;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving;
    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
