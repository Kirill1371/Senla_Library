package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.mapper.StaffMapper;
import com.lib.library.impl.Repository.StaffRepository;
import com.lib.library.impl.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository repository;
    private final StaffMapper mapper;

    @Override
    public StaffDto create(StaffDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public StaffDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<StaffDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StaffDto update(Long id, StaffDto dto) {
        Staff entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
