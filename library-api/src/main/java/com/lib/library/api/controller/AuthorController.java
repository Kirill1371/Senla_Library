package com.lib.library.api.controller;

import com.lib.library.api.dto.AuthorDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/authors")
public interface AuthorController {

    @PostMapping
    AuthorDto create(@Valid @RequestBody AuthorDto dto);

    @GetMapping("/{id}")
    AuthorDto getById(@PathVariable Long id);

    @GetMapping
    List<AuthorDto> getAll();

    @PutMapping("/{id}")
    AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}