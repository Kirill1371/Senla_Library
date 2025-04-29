package com.lib.library.impl.Controller;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.impl.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService service;

    @PostMapping
    public StaffDto create(@RequestBody StaffDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public StaffDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<StaffDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public StaffDto update(@PathVariable Long id, @RequestBody StaffDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
