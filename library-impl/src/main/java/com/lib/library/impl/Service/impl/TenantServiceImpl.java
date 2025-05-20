package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.TenantDto;
import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.mapper.TenantMapper;
import com.lib.library.impl.Repository.TenantRepository;
import com.lib.library.impl.Service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository repository;
    private final TenantMapper mapper;

    @Override
    public TenantDto create(TenantDto dto) {
        Tenant entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public TenantDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<TenantDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TenantDto update(Long id, TenantDto dto) {
        Tenant entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        return mapper.toDto(repository.update(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}