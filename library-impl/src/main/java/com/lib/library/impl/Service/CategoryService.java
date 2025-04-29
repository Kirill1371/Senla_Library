package com.lib.library.impl.Service;

import com.lib.library.api.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto dto);
    CategoryDto getById(Long id);
    List<CategoryDto> getAll();
    CategoryDto update(Long id, CategoryDto dto);
    void delete(Long id);
}
