package com.lib.library.impl.service;

import com.lib.library.api.dto.TenantDto;

import java.util.List;

public interface TenantService {
    TenantDto create(TenantDto dto);
    TenantDto getById(Long id);
    List<TenantDto> getAll();
    TenantDto update(Long id, TenantDto dto);
    void delete(Long id);
}
