package com.lib.library.impl.mapper;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.db.entity.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReaderMapper {


    ReaderDto toDto(Reader reader);

    @Mapping(target = "id", ignore = true)
    Reader toEntity(ReaderDto dto);
}
