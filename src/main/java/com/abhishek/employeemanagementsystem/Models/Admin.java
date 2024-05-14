package com.abhishek.employeemanagementsystem.Models;

import com.abhishek.employeemanagementsystem.Services.DepartmentService;
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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    @ManyToOne
    private Department department;
    private LocalDate dateOfJoining;
    private UserType userType = UserType.ADMIN;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;
    private LocalDate dateCreated;

    @ManyToOne
    private DepartmentManager departmentManager;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
