package com.abhishek.employeemanagementsystem.Dtos;

import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginResponseDto {
    private Long id;
    private String username;
    private String message;
    private LoginStatus loginStatus;
}
