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
    List<Long> employeeIds = new ArrayList<>();
    @ElementCollection
    List<Long> departmentMemberIds = new ArrayList<>();
    @ElementCollection
    List<Long> roleIds = new ArrayList<>();
    @ElementCollection
    List<Long> adminIds = new ArrayList<>();
    @OneToMany(mappedBy = "department")
    List<Teams> teams = new ArrayList<>();
}
