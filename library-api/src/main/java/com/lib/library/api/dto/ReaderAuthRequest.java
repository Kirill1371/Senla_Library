package com.lib.library.api.dto;

import lombok.Data;

@Data
public class ReaderAuthRequest {
    private String name;
    private String password;
}
