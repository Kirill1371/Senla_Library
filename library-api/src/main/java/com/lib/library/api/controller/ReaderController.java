package com.lib.library.api.controller;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.api.dto.ReaderAuthRequest;
import com.lib.library.api.dto.ReaderAuthResponse;
import com.lib.library.api.dto.ReaderDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/readers")
public interface ReaderController {

    @PostMapping("/login")
    ReaderAuthResponse login(@RequestBody ReaderAuthRequest request);

    @GetMapping("/book-loans")
    ResponseEntity<List<BookLoanDto>> getOwnBookLoans();

    @PostMapping
    ReaderDto create(@Valid @RequestBody ReaderDto dto);

    @GetMapping("/{id}")
    ReaderDto getById(@PathVariable Long id);

    @GetMapping
    List<ReaderDto> getAll();

    @PutMapping("/{id}")
    ReaderDto update(@PathVariable Long id, @RequestBody ReaderDto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}