package com.lib.library.impl.mapper;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Author;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "staffId", source = "staff.id")
    AuthorDto toDto(Author author);

    //@Mapping(target = "staff", ignore = true)
    @Mapping(target = "staff.id", source = "staffId")
    Author toEntity(AuthorDto dto);

    // Для обновления существующего entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "staff.id", source = "staffId")
    void updateAuthorFromDto(AuthorDto dto, @MappingTarget Author entity);
}