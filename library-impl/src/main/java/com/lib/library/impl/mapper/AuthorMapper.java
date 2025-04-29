package com.lib.library.impl.mapper;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto dto);
}
