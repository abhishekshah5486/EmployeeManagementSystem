package com.abhishek.employeemanagementsystem.Dtos;

import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Models.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginRequestDto {
    private String username;
    private String password;
}
