package com.abhishek.employeemanagementsystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamLeaderResponseDto {
    private int id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private String message;
}
