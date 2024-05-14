package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ElementCollection
    private List<Long> roleIds = new ArrayList<>();
    @ElementCollection
    private List<Long> adminIds = new ArrayList<>();
    @OneToMany
    private List<Teams> teams = new ArrayList<>();
    @ElementCollection
    private List<Long> departmentManagerIds = new ArrayList<>();
    @OneToOne
    private OperationsManager operationsManager;
}
