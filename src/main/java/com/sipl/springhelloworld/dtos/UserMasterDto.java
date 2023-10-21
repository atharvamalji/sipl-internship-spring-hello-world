package com.sipl.springhelloworld.dtos;

import java.util.List;

import com.sipl.springhelloworld.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMasterDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
}
