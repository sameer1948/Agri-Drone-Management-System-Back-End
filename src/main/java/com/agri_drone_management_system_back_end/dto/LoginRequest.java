package com.agri_drone_management_system_back_end.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}