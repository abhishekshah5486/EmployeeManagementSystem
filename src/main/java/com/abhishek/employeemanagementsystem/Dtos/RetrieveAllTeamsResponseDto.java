package com.abhishek.employeemanagementsystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveAllTeamsResponseDto {
    private Long id;
    private String name;
    private String description;
}
