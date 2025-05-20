package com.lib.library.api.dto;

import lombok.Data;

@Data
public class ReaderAuthRequestDto {
    private String name;
    private String password;
}