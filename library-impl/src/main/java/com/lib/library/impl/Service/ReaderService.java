package com.lib.library.impl.Service;

import com.lib.library.api.dto.ReaderDto;

import java.util.List;

public interface ReaderService {
    ReaderDto create(ReaderDto dto);
    ReaderDto getById(Long id);
    List<ReaderDto> getAll();
    ReaderDto update(Long id, ReaderDto dto);
    void delete(Long id);
}