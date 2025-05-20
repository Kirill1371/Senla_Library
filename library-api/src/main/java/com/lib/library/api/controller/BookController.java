package com.lib.library.api.controller;

import com.lib.library.api.dto.BookDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/books")
public interface BookController {

    @PostMapping
    ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto);

    @GetMapping("/{id}")
    ResponseEntity<BookDto> getById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<BookDto>> getAll();

    @PutMapping("/{id}")
    ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto bookDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}