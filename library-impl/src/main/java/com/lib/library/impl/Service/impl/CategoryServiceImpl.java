//package com.lib.library.impl.Service.impl;
//
//import com.lib.library.api.dto.CategoryDto;
//import com.lib.library.db.entity.Category;
//import com.lib.library.impl.mapper.CategoryMapper;
//import com.lib.library.impl.Repository.CategoryRepository;
//import com.lib.library.impl.Service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryServiceImpl implements CategoryService {
//    private final CategoryRepository repository;
//    private final CategoryMapper mapper;
//
//    @Override
//    public CategoryDto create(CategoryDto dto) {
//        return mapper.toDto(repository.save(mapper.toEntity(dto)));
//    }
//
//    @Override
//    public CategoryDto getById(Long id) {
//        return repository.findById(id).map(mapper::toDto).orElse(null);
//    }
//
//    @Override
//    public List<CategoryDto> getAll() {
//        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public CategoryDto update(Long id, CategoryDto dto) {
//        Category entity = repository.findById(id).orElseThrow();
//        entity.setName(dto.getName());
//        return mapper.toDto(repository.save(entity));
//    }
//
//    @Override
//    public void delete(Long id) {
//        repository.deleteById(id);
//    }
//}


package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Category;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.Repository.CategoryRepository;
import com.lib.library.impl.Repository.StaffRepository;
import com.lib.library.impl.mapper.CategoryMapper;
import com.lib.library.impl.Service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StaffRepository staffRepository;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if (!staffRepository.existsById(categoryDto.getStaffId())) {
            throw new EntityNotFoundException("Staff not found with id: " + categoryDto.getStaffId());
        }
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

//    @Override
//    public CategoryDto save(CategoryDto categoryDto) {
//        // Проверяем, что staffId присутствует
//        if (categoryDto.getStaffId() == null) {
//            throw new IllegalArgumentException("Staff ID cannot be null");
//        }
//
//        // Проверяем существование Staff
//        if (!staffRepository.existsById(categoryDto.getStaffId())) {
//            throw new EntityNotFoundException("Staff not found with id: " + categoryDto.getStaffId());
//        }
//
//        Category category = categoryMapper.toEntity(categoryDto);
//        return categoryMapper.toDto(categoryRepository.save(category));
//    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public CategoryDto update(Long id, CategoryDto categoryDto) {
//        Category existing = categoryRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
//        Category updated = categoryMapper.toEntity(categoryDto);
//        updated.setId(existing.getId());
//
//
//        // Обрабатываем parentCategory (если есть в DTO)
//        if (categoryDto.getParentId() != null) {
//            Category parent = categoryRepository.findById(categoryDto.getParentId())
//                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found: " + categoryDto.getParentId()));
//            existing.setParentCategory(parent);
//        } else {
//            existing.setParentCategory(null); // или оставляем как есть
//        }
//
//        // Обрабатываем staff (обязательное поле)
//        if (categoryDto.getStaffId() != null) {
//            Staff staff = staffRepository.findById(categoryDto.getStaffId())
//                    .orElseThrow(() -> new EntityNotFoundException("Staff not found: " + categoryDto.getStaffId()));
//            existing.setStaff(staff);
//        } else {
//            throw new IllegalArgumentException("Staff ID cannot be null for category");
//        }
//
//
//        return categoryMapper.toDto(categoryRepository.save(updated));
//    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));

        //existing.setName(categoryDto.getName());

        if (categoryDto.getName() != null) {
            existing.setName(categoryDto.getName());
        }

        // Обработка parentCategory
        if (categoryDto.getParentId() != null) {
            Category parent = categoryRepository.findById(categoryDto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent category not found: " + categoryDto.getParentId()));
            existing.setParentCategory(parent);
        } else {
            existing.setParentCategory(null);
        }

        // Обработка staff (обязательное поле)
        Staff staff = staffRepository.findById(categoryDto.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Staff not found: " + categoryDto.getStaffId()));
        existing.setStaff(staff);

        return categoryMapper.toDto(categoryRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> findSubCategories(Long parentId) {
        List<Category> subCategories = categoryRepository.findByParentCategoryId(parentId);
        return subCategories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
