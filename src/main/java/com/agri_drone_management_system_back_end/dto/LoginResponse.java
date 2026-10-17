package com.agri_drone_management_system_back_end.dto;

import com.agri_drone_management_system_back_end.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String username;
    private Role role;

}