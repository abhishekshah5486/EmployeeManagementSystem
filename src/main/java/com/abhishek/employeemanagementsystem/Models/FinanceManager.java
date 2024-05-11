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
public class FinanceManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Department department;
    @OneToMany
    private List<Teams> teamsList = new ArrayList<>();
    @OneToMany
    private List<Project> projectList = new ArrayList<>();
    private LocalDate dateOfJoining;
    private UserType userType = UserType.FINANCE_MANAGER;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;

    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
