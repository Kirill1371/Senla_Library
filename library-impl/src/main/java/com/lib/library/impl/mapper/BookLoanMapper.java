package com.lib.library.impl.mapper;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.db.entity.BookLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {

    @Mapping(target = "readerId", source = "reader.id")
    @Mapping(target = "bookId", source = "book.id")
    BookLoanDto toDto(BookLoan bookLoan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reader.id", source = "readerId")
    @Mapping(target = "book.id", source = "bookId")
    BookLoan toEntity(BookLoanDto dto);
}
