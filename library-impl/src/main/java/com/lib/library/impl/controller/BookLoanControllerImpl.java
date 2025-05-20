package com.lib.library.impl.controller;

import com.lib.library.api.controller.BookLoanController;
import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.impl.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookLoanControllerImpl implements BookLoanController {

    private final BookLoanService service;

    @Override
    public BookLoanDto create(BookLoanDto dto) {
        return service.create(dto);
    }

    @Override
    public BookLoanDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public List<BookLoanDto> getAll() {
        return service.getAll();
    }

    @Override
    public BookLoanDto update(Long id, BookLoanDto dto) {
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}