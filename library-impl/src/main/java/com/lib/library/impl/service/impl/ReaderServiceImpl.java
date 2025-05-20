package com.lib.library.impl.service.impl;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.mapper.ReaderMapper;
import com.lib.library.impl.repository.ReaderRepository;
import com.lib.library.impl.service.ReaderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository repository;
    private final ReaderMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ReaderDto create(ReaderDto dto) {
        Reader reader = mapper.toEntity(dto);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        reader.setPassword(encodedPassword);
        return mapper.toDto(repository.save(reader));
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
        return mapper.toDto(repository.update(entity));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Id don`t found " + id));
        repository.deleteById(id);
    }
}
