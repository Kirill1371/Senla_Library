package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Author;
import com.lib.library.impl.mapper.AuthorMapper;
import com.lib.library.impl.Repository.AuthorRepository;
import com.lib.library.impl.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lib.library.impl.Repository.StaffRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final AuthorMapper mapper;
    private final StaffRepository staffRepository;

    @Override
    public AuthorDto create(AuthorDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public AuthorDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<AuthorDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        Author entity = repository.findById(id).orElseThrow();

        mapper.updateAuthorFromDto(dto, entity); // Только непустые поля попадут
        Author updatedAuthor = repository.update(entity);
        return mapper.toDto(updatedAuthor);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
