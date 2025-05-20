package com.lib.library.api.controller;

import com.lib.library.api.dto.StaffDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/staff")
public interface StaffController {

    @PostMapping
    StaffDto create(@Valid @RequestBody StaffDto dto);

    @GetMapping("/{id}")
    StaffDto getById(@PathVariable Long id);

    @GetMapping
    List<StaffDto> getAll();

    @PutMapping("/{id}")
    StaffDto update(@PathVariable Long id, @RequestBody StaffDto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}