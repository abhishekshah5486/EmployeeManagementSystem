package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private UserType userType = UserType.DEPARTMENT_MANAGER;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving;
    private LocalDate dateCreated;
    @ManyToOne
    private OperationsManager operationsManager;
    @OneToMany
    private List<Admin> admins = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
