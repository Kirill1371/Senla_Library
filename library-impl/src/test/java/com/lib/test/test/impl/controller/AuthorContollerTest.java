package com.lib.test.test.impl.controller;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.impl.controller.AuthorControllerImpl;
import com.lib.library.impl.service.AuthorService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import com.lib.library.db.entity.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthorControllerImpl controller;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        // Mock SecurityContext
        SecurityContext context = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("test@example.com");
        when(context.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(context);

        Staff staff = new Staff();
        staff.setId(1L);
        when(userDetailsService.getStaffByEmail("test@example.com")).thenReturn(staff);
    }

    @Test
    void testCreate() {
        AuthorDto dto = new AuthorDto();
        when(authorService.create(any())).thenReturn(dto);

        AuthorDto result = controller.create(dto);

        assertEquals(dto, result);
        verify(authorService).create(dto);
    }

    @Test
    void testGetById() {
        AuthorDto dto = new AuthorDto();
        when(authorService.getById(1L)).thenReturn(dto);

        AuthorDto result = controller.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetAll() {
        List<AuthorDto> list = List.of(new AuthorDto(), new AuthorDto());
        when(authorService.getAll()).thenReturn(list);

        List<AuthorDto> result = controller.getAll();
        assertEquals(list.size(), result.size());
    }

    @Test
    void testUpdate() {
        AuthorDto dto = new AuthorDto();
        when(authorService.update(eq(1L), any())).thenReturn(dto);

        AuthorDto result = controller.update(1L, dto);
        assertEquals(dto, result);
    }

    @Test
    void testDelete() {
        doNothing().when(authorService).delete(1L);
        controller.delete(1L);
        verify(authorService).delete(1L);
    }
}
