package com.lib.library.impl.Service;

import com.lib.library.api.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    AuthorDto create(AuthorDto dto);
    AuthorDto getById(Long id);
    List<AuthorDto> getAll();
    AuthorDto update(Long id, AuthorDto dto);
    void delete(Long id);
}
