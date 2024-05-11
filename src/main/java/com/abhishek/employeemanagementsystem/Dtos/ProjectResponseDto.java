package com.abhishek.employeemanagementsystem.Dtos;

import com.abhishek.employeemanagementsystem.Models.ProjectPriorityLevel;
import com.abhishek.employeemanagementsystem.Models.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String description;
    private ProjectStatus projectStatus;
    private LocalDate startDate;
    private LocalDate endDate;
}
