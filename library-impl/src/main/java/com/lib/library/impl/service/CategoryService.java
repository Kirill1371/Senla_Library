//package com.lib.library.impl.Service;
//
//import com.lib.library.api.dto.CategoryDto;
//
//import java.util.List;
//
//public interface CategoryService {
//    CategoryDto create(CategoryDto dto);
//    CategoryDto getById(Long id);
//    List<CategoryDto> getAll();
//    CategoryDto update(Long id, CategoryDto dto);
//    void delete(Long id);
//}


package com.lib.library.impl.service;

import com.lib.library.api.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto findById(Long id);
    List<CategoryDto> findAll();
    CategoryDto update(Long id, CategoryDto categoryDto);
    void delete(Long id);
    List<CategoryDto> findSubCategories(Long parentId);
}
