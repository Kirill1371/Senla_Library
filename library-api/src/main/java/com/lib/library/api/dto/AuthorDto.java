package com.lib.library.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private LocalDate birthDay;
    private String country;
    private Long staffId;
}
