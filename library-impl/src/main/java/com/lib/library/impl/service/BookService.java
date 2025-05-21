package com.lib.library.impl.service;

import com.lib.library.api.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto save(BookDto bookDto);
    BookDto findById(Long id);
    List<BookDto> findAll();
    BookDto update(Long id, BookDto bookDto);
    void delete(Long id);
}