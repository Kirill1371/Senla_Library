package com.lib.library.impl.mapper;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.db.entity.BookLoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {
    BookLoanDto toDto(BookLoan bookLoan);
    BookLoan toEntity(BookLoanDto dto);
}
