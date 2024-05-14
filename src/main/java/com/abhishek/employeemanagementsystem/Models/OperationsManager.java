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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OperationsManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    @OneToOne
    private Department department;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;
    private UserType userType = UserType.OPERATIONS_MANAGER;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving;
    private LocalDate dateCreated;

    @ManyToOne
    private Executive executive;
    @OneToMany
    private List<DepartmentManager> departmentManagers = new ArrayList<>();
    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
    @ElementCollection
    List<Long> departmentManagerIds = new ArrayList<>();
}
