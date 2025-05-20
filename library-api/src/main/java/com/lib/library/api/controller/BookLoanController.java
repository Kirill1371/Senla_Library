package com.lib.library.api.controller;

import com.lib.library.api.dto.BookLoanDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/bookloans")
public interface BookLoanController {

    @PostMapping
    BookLoanDto create(@Valid @RequestBody BookLoanDto dto);

    @GetMapping("/{id}")
    BookLoanDto getById(@PathVariable Long id);

    @GetMapping
    List<BookLoanDto> getAll();

    @PutMapping("/{id}")
    BookLoanDto update(@PathVariable Long id, @RequestBody BookLoanDto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}