package com.lib.library.api.controller;

import com.lib.library.api.dto.CategoryDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
public interface CategoryController {

    @PostMapping
    ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto);

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> getById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<CategoryDto>> getAll();

    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping("/parent/{parentId}")
    ResponseEntity<List<CategoryDto>> getSubCategories(@PathVariable Long parentId);
}