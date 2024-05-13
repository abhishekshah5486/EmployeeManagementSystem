package com.abhishek.employeemanagementsystem.Dtos;

import com.abhishek.employeemanagementsystem.Models.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequestDto {
    private String title;
    private String description;
    private ProjectStatus projectStatus = ProjectStatus.PLANNED;
    private LocalDate startDate;
}
