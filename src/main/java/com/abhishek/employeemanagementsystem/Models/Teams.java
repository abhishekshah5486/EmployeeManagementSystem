package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private TeamLeader teamLeader;
    @OneToMany(mappedBy = "team")
    List<Employee> employees;
    @ManyToOne
    private Department department;
    @ManyToMany
    List<Admin> admins = new ArrayList<>();
    @ManyToOne
    private ProjectManager projectManager;
    @ManyToOne
    private FinanceManager financeManager;
    @ManyToOne
    private MarketManager marketManager;
    @ManyToOne
    private TechnicalManager technicalManager;
    @ManyToOne
    private RiskManager riskManager;
    @OneToMany
    private List<Project> projects = new ArrayList<>();
}
