package com.lib.library.api.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String name;
    private String email;
    private String password;
    private String role;
    private Long tenantId;
}
