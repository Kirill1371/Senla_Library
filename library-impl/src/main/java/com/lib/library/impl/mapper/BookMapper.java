package com.lib.library.impl.mapper;

import com.lib.library.api.dto.BookDto;
import com.lib.library.db.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "title", source = "book.title")
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "categoryId", source = "category.id")
    BookDto toDto(Book book);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "author.id", source = "authorId")
    @Mapping(target = "category.id", source = "categoryId")
    Book toEntity(BookDto dto);
}
