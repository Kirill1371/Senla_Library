package com.lib.library.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Long authorId;
    private Long categoryId;
    private Integer publishYear;
    private Boolean available;
}
