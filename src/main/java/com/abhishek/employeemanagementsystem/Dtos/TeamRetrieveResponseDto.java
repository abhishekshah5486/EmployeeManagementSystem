package com.abhishek.employeemanagementsystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRetrieveResponseDto {
    private Long id;
    private String name;
    private String description;
    private TeamLeaderResponseDto teamLeaderResponseDto;
    private List<EmployeeResponseDto> employeeResponseDtos;
    private DepartmentResponseDto departmentResponseDto;
    private List<AdminResponseDto> adminResponseDtos;
    private ProjectManagerResponseDto projectManagerResponseDto;
    private FinanceManagerResponseDto financeManagerResponseDto;
    private MarketManagerResponseDto marketManagerResponseDto;
    private TechnicalManagerResponseDto technicalManagerResponseDto;
    private RiskManagerResponseDto riskManagerResponseDto;
    private List<ProjectResponseDto> projectResponseDtos;
}
