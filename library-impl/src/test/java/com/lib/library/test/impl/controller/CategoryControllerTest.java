package com.lib.library.test.impl.controller;

import com.lib.library.api.dto.CategoryDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.controller.CategoryControllerImpl;
import com.lib.library.impl.service.CategoryService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerImplTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private CategoryControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(authentication.getName()).thenReturn("user@example.com");
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Staff staff = new Staff();
        staff.setId(100L);
        when(userDetailsService.getStaffByEmail("user@example.com")).thenReturn(staff);
    }

    @Test
    void testCreate() {
        CategoryDto dto = new CategoryDto();
        dto.setName("Fiction");

        CategoryDto saved = new CategoryDto();
        saved.setId(1L);
        saved.setName("Fiction");

        when(categoryService.save(any(CategoryDto.class))).thenReturn(saved);

        ResponseEntity<CategoryDto> response = controller.create(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fiction", response.getBody().getName());
        assertEquals(100L, dto.getStaffId());
    }

    @Test
    void testGetById() {
        CategoryDto dto = new CategoryDto();
        dto.setId(1L);

        when(categoryService.findById(1L)).thenReturn(dto);

        ResponseEntity<CategoryDto> response = controller.getById(1L);

        assertEquals(1L, response.getBody().getId());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetAll() {
        List<CategoryDto> list = List.of(new CategoryDto(), new CategoryDto());

        when(categoryService.findAll()).thenReturn(list);

        ResponseEntity<List<CategoryDto>> response = controller.getAll();

        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testUpdate() {
        CategoryDto dto = new CategoryDto();
        dto.setName("Updated");

        CategoryDto updated = new CategoryDto();
        updated.setId(1L);
        updated.setName("Updated");

        when(categoryService.update(eq(1L), any(CategoryDto.class))).thenReturn(updated);

        ResponseEntity<CategoryDto> response = controller.update(1L, dto);

        assertEquals("Updated", response.getBody().getName());
        assertEquals(100L, dto.getStaffId());
    }

    @Test
    void testDelete() {
        doNothing().when(categoryService).delete(1L);

        ResponseEntity<Void> response = controller.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(categoryService).delete(1L);
    }

    @Test
    void testGetSubCategories() {
        List<CategoryDto> subs = List.of(new CategoryDto(), new CategoryDto());
        when(categoryService.findSubCategories(2L)).thenReturn(subs);

        ResponseEntity<List<CategoryDto>> response = controller.getSubCategories(2L);

        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }
}
