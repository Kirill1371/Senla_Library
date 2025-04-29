package com.lib.library.impl.mapper;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto dto);
}
