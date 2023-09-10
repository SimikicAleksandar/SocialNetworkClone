package com.example.swt_kwt_aleksandar_simikic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String image;
}
