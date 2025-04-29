package com.lib.library.api.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private Long authorId;
    private Long categoryId;
    private Integer publishYear;
    private Boolean available;
}
