package com.lib.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    private Long id;
    private String name;
    private String password;
}
