//package com.lib.library.impl.mapper;
//
//import com.lib.library.api.dto.CategoryDto;
//import com.lib.library.db.entity.Category;
//import org.mapstruct.Mapper;
//
//@Mapper(componentModel = "spring")
//public interface CategoryMapper {
//    CategoryDto toDto(Category category);
//    Category toEntity(CategoryDto dto);
//}


package com.lib.library.impl.mapper;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parentCategory.id", target = "parentId")
    @Mapping(source = "staff.id", target = "staffId")
    CategoryDto toDto(Category category);

    @Mapping(target = "parentCategory.id", source = "parentId")
    @Mapping(target = "staff.id", source = "staffId")
    Category toEntity(CategoryDto dto);
}
