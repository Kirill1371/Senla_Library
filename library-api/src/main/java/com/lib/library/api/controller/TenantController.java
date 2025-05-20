package com.lib.library.api.controller;

import com.lib.library.api.dto.TenantDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/tenants")
public interface TenantController {

    @PostMapping
    TenantDto create(@Valid @RequestBody TenantDto dto);

    @GetMapping("/{id}")
    TenantDto getById(@PathVariable Long id);

    @GetMapping
    List<TenantDto> getAll();

    @PutMapping("/{id}")
    TenantDto update(@PathVariable Long id, @RequestBody TenantDto dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}