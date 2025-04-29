package com.lib.library.impl.Service;

import com.lib.library.api.dto.BookLoanDto;

import java.util.List;

public interface BookLoanService {
    BookLoanDto create(BookLoanDto dto);
    BookLoanDto getById(Long id);
    List<BookLoanDto> getAll();
    BookLoanDto update(Long id, BookLoanDto dto);
    void delete(Long id);
}
