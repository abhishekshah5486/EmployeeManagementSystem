package com.abhishek.employeemanagementsystem.Models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(nullable = true)
    private ProjectStatus projectStatus;
    private ProjectPriorityLevel priorityLevel;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany
    private List<Employee> employees;
//    @ManyToOne
//    private Teams team;
}
