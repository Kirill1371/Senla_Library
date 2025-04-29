package com.lib.library.api.dto;

import lombok.Data;

@Data
public class StaffDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;
    private Long tenantId;
}
