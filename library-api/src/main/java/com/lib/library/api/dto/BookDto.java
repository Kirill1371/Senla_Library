package com.lib.library.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Valid
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookDto {
    private Long id;
    @NotNull
    private String title;
    private Long authorId;
    private Long categoryId;
    private Integer publishYear;
    private Boolean available;
}
