package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;

public class OperationsManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Department department;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private LocalDate dateOfJoining;
    private LocalDate dateOfLeaving;
    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
