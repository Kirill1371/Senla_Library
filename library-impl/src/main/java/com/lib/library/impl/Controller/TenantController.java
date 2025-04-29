package com.lib.library.impl.Controller;

import com.lib.library.api.dto.TenantDto;
import com.lib.library.impl.Service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService service;

    @PostMapping
    public TenantDto create(@RequestBody TenantDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public TenantDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<TenantDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public TenantDto update(@PathVariable Long id, @RequestBody TenantDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
