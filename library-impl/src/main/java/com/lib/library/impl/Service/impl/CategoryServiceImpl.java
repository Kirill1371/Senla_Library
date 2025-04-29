package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Category;
import com.lib.library.impl.mapper.CategoryMapper;
import com.lib.library.impl.Repository.CategoryRepository;
import com.lib.library.impl.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryDto create(CategoryDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public CategoryDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
