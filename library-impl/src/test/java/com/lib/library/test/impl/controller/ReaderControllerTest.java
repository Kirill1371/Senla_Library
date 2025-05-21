package com.lib.library.test.impl.controller;

import com.lib.library.api.dto.*;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.controller.ReaderControllerImpl;
import com.lib.library.impl.service.BookLoanService;
import com.lib.library.impl.service.ReaderAuthService;
import com.lib.library.impl.service.ReaderService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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

class ReaderControllerImplTest {

    @InjectMocks
    private ReaderControllerImpl controller;

    @Mock
    private ReaderService readerService;

    @Mock
    private ReaderAuthService authService;

    @Mock
    private BookLoanService bookLoanService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    private ReaderDto readerDto;
    private Reader reader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        readerDto = new ReaderDto();
        readerDto.setId(1L);
        readerDto.setName("Test");

        reader = new Reader();
        reader.setId(1L);
        reader.setName("Test");
    }

    @Test
    void testLogin() {
        ReaderAuthRequestDto req = new ReaderAuthRequestDto();
        req.setName("user");
        req.setPassword("pass");

        ReaderAuthResponseDto expectedResponse = new ReaderAuthResponseDto("token");

        when(authService.authenticate(req)).thenReturn(expectedResponse);

        ReaderAuthResponseDto result = controller.login(req);

        assertEquals(expectedResponse, result);
    }


    @Test
    void testGetOwnBookLoans() {
        // Mock SecurityContext
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("user");
        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        List<BookLoanDto> loans = List.of(new BookLoanDto());

        when(userDetailsService.getReaderByName("user")).thenReturn(reader);
        when(bookLoanService.findByReaderId(1L)).thenReturn(loans);

        ResponseEntity<List<BookLoanDto>> response = controller.getOwnBookLoans();

        assertEquals(loans, response.getBody());
    }

    @Test
    void testCreate() {
        when(readerService.create(readerDto)).thenReturn(readerDto);

        ReaderDto result = controller.create(readerDto);

        assertEquals(readerDto, result);
    }

    @Test
    void testGetById() {
        when(readerService.getById(1L)).thenReturn(readerDto);

        ReaderDto result = controller.getById(1L);

        assertEquals(readerDto, result);
    }

    @Test
    void testGetAll() {
        List<ReaderDto> readers = List.of(readerDto);
        when(readerService.getAll()).thenReturn(readers);

        List<ReaderDto> result = controller.getAll();

        assertEquals(readers, result);
    }

    @Test
    void testUpdate() {
        when(readerService.update(1L, readerDto)).thenReturn(readerDto);

        ReaderDto result = controller.update(1L, readerDto);

        assertEquals(readerDto, result);
    }

    @Test
    void testDelete() {
        controller.delete(1L);

        verify(readerService).delete(1L);
    }
}
