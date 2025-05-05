package com.lib.library.impl.Controller;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.impl.Service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping
    public AuthorDto create(@RequestBody AuthorDto dto) {
        System.out.println("Received DTO: " + dto);
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto dto) {
        System.out.println("Received DTO: " + dto);
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
