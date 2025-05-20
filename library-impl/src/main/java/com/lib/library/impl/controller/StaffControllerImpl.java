package com.lib.library.impl.controller;

import com.lib.library.api.controller.StaffController;
import com.lib.library.api.dto.StaffDto;
import com.lib.library.impl.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StaffControllerImpl implements StaffController {

    private final StaffService service;

    @Override
    public StaffDto create(StaffDto dto) {
        return service.create(dto);
    }

    @Override
    public StaffDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public List<StaffDto> getAll() {
        return service.getAll();
    }

    @Override
    public StaffDto update(Long id, StaffDto dto) {
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}