package com.lib.library.impl.Controller;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.impl.Service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService service;

    @PostMapping
    public ReaderDto create(@RequestBody ReaderDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public ReaderDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ReaderDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ReaderDto update(@PathVariable Long id, @RequestBody ReaderDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
