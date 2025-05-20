package com.lib.library.impl.mapper;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.db.entity.Reader;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReaderMapper {


    ReaderDto toDto(Reader reader);

    @Mapping(target = "id", ignore = true)
    Reader toEntity(ReaderDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateReaderFromDto(ReaderDto dto, @MappingTarget Reader entity);
}
