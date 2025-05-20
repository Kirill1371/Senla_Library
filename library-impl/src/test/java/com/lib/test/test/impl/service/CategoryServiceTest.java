package com.lib.test.test.impl.service;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Category;
import com.lib.library.impl.repository.CategoryRepository;
import com.lib.library.impl.repository.StaffRepository;
import com.lib.library.impl.service.impl.CategoryServiceImpl;
import com.lib.library.impl.mapper.CategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        CategoryDto dto = new CategoryDto();
        Category entity = new Category();
        Category saved = new Category();
        CategoryDto resultDto = new CategoryDto();

        when(categoryMapper.toEntity(dto)).thenReturn(entity);
        when(categoryRepository.save(entity)).thenReturn(saved);
        when(categoryMapper.toDto(saved)).thenReturn(resultDto);

        CategoryDto result = categoryService.save(dto);
        assertEquals(resultDto, result);
    }

    @Test
    void testFindById() {
        Category category = new Category();
        CategoryDto dto = new CategoryDto();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(dto);

        CategoryDto result = categoryService.findById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testFindById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(1L));
    }

    @Test
    void testFindAll() {
        List<Category> categories = List.of(new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.toDto(any(Category.class))).thenReturn(new CategoryDto());

        List<CategoryDto> result = categoryService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        CategoryDto dto = new CategoryDto();
        Category existing = new Category();
        Category updated = new Category();
        CategoryDto resultDto = new CategoryDto();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existing));
        doNothing().when(categoryMapper).updateCategoryFromDto(dto, existing);
        when(categoryRepository.update(existing)).thenReturn(updated);
        when(categoryMapper.toDto(updated)).thenReturn(resultDto);

        CategoryDto result = categoryService.update(1L, dto);
        assertEquals(resultDto, result);
    }

    @Test
    void testUpdate_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoryService.update(1L, new CategoryDto()));
    }

    @Test
    void testDelete() {
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.delete(1L);
        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void testFindSubCategories() {
        List<Category> subCats = List.of(new Category(), new Category());
        when(categoryRepository.findByParentCategoryId(1L)).thenReturn(subCats);
        when(categoryMapper.toDto(any(Category.class))).thenReturn(new CategoryDto());

        List<CategoryDto> result = categoryService.findSubCategories(1L);
        assertEquals(2, result.size());
    }
}
