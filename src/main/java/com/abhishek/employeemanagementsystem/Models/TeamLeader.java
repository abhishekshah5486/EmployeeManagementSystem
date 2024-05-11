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
public class TeamLeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Department department;
    private LocalDate dateOfJoining;
    private UserType userType = UserType.TEAM_LEADER;
    private LoginStatus loginStatus = LoginStatus.LOGGEDOUT;
    private EmploymentStatus employmentStatus = EmploymentStatus.ACTIVE;
    @OneToOne
    private Teams team;
    @ElementCollection
    private List<Long> memberIds = new ArrayList<>();

    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
    }
}
