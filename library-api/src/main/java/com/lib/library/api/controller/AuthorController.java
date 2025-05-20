//package com.lib.library.api.controller;
//
//import com.lib.library.api.dto.AuthorDto;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RequestMapping("/api/authors")
//public interface AuthorController {
//
//    @PostMapping
//    AuthorDto create(@Valid @RequestBody AuthorDto dto);
//
//    @GetMapping("/{id}")
//    AuthorDto getById(@PathVariable Long id);
//
//    @GetMapping
//    List<AuthorDto> getAll();
//
//    @PutMapping("/{id}")
//    AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto dto);
//
//    @DeleteMapping("/{id}")
//    void delete(@PathVariable Long id);
//}

package com.lib.library.api.controller;

import com.lib.library.api.dto.AuthorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/authors")
@Tag(name = "Author Controller", description = "CRUD операции с авторами")
public interface AuthorController {

    @PostMapping
    @Operation(summary = "Создать автора")
    AuthorDto create(@RequestBody AuthorDto dto);

    @GetMapping("/{id}")
    @Operation(summary = "Получить автора по ID")
    AuthorDto getById(@PathVariable Long id);

    @GetMapping
    @Operation(summary = "Получить список всех авторов")
    List<AuthorDto> getAll();

    @PutMapping("/{id}")
    @Operation(summary = "Обновить автора по ID")
    AuthorDto update(@PathVariable Long id, @RequestBody AuthorDto dto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора по ID")
    void delete(@PathVariable Long id);
}
