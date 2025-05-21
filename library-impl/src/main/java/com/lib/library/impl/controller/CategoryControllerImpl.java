package com.lib.library.impl.controller;

import com.lib.library.api.controller.CategoryController;
import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.service.CategoryService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<CategoryDto> create(CategoryDto categoryDto) {
        setCurrentStaffId(categoryDto);
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> getById(Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Override
    public ResponseEntity<CategoryDto> update(Long id, CategoryDto categoryDto) {
        setCurrentStaffId(categoryDto);
        return ResponseEntity.ok(categoryService.update(id, categoryDto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getSubCategories(Long parentId) {
        return ResponseEntity.ok(categoryService.findSubCategories(parentId));
    }

    private void setCurrentStaffId(CategoryDto categoryDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Staff staff = userDetailsService.getStaffByEmail(username);
        categoryDto.setStaffId(staff.getId());
    }
}