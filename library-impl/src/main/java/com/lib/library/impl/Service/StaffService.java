package com.lib.library.impl.Service;

import com.lib.library.api.dto.StaffDto;

import java.util.List;

public interface StaffService {
    StaffDto create(StaffDto dto);
    StaffDto getById(Long id);
    List<StaffDto> getAll();
    StaffDto update(Long id, StaffDto dto);
    void delete(Long id);
}