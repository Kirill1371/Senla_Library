//package com.lib.library.impl.Controller;
//
//import com.lib.library.api.dto.CategoryDto;
//import com.lib.library.impl.Service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categories")
//@RequiredArgsConstructor
//public class CategoryController {
//    private final CategoryService service;
//
//    @PostMapping
//    public CategoryDto create(@RequestBody CategoryDto dto) {
//        return service.create(dto);
//    }
//
//    @GetMapping("/{id}")
//    public CategoryDto getById(@PathVariable Long id) {
//        return service.getById(id);
//    }
//
//    @GetMapping
//    public List<CategoryDto> getAll() {
//        return service.getAll();
//    }
//
//    @PutMapping("/{id}")
//    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto dto) {
//        return service.update(id, dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.delete(id);
//    }
//}




package com.lib.library.impl.Controller;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.impl.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.update(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CategoryDto>> getSubCategories(@PathVariable Long parentId) {
        return ResponseEntity.ok(categoryService.findSubCategories(parentId));
    }
}
