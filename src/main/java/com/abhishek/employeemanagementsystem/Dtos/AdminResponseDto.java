package com.abhishek.employeemanagementsystem.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponseDto {
    private Long id;
    private String name;
    private String email;
    private String username;
}
