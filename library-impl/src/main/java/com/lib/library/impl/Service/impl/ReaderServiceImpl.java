package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.mapper.ReaderMapper;
import com.lib.library.impl.Repository.ReaderRepository;
import com.lib.library.impl.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository repository;
    private final ReaderMapper mapper;

    @Override
    public ReaderDto create(ReaderDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public ReaderDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Reader not found with id" + id));
    }

    @Override
    public List<ReaderDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ReaderDto update(Long id, ReaderDto dto) {
        Reader entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
