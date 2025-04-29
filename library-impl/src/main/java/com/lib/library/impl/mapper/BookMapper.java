package com.lib.library.impl.mapper;

import com.lib.library.api.dto.BookDto;
import com.lib.library.db.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toEntity(BookDto dto);
}
