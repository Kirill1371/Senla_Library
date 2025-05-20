package com.lib.library.impl.service;

import com.lib.library.api.dto.BookLoanDto;

import java.util.List;

public interface BookLoanService {
    BookLoanDto create(BookLoanDto dto);
    List<BookLoanDto> findByReaderId(Long readerId);
    BookLoanDto getById(Long id);
    List<BookLoanDto> getAll();
    BookLoanDto update(Long id, BookLoanDto dto);
    void delete(Long id);
}
