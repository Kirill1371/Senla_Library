package com.lib.library.impl.controller;

import com.lib.library.api.controller.TenantController;
import com.lib.library.api.dto.TenantDto;
import com.lib.library.impl.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TenantControllerImpl implements TenantController {

    private final TenantService service;

    @Override
    public TenantDto create(TenantDto dto) {
        return service.create(dto);
    }

    @Override
    public TenantDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public List<TenantDto> getAll() {
        return service.getAll();
    }

    @Override
    public TenantDto update(Long id, TenantDto dto) {
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}