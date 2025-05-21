package com.lib.library.test.impl.controller;


import com.lib.library.api.dto.BookDto;
import com.lib.library.impl.service.BookService;
import com.lib.library.impl.controller.BookControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookControllerImpl bookController;

    private final BookDto bookDto = new BookDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldReturnCreatedBook() {
        when(bookService.save(bookDto)).thenReturn(bookDto);

        ResponseEntity<BookDto> response = bookController.create(bookDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookDto, response.getBody());
    }

    @Test
    void getById_ShouldReturnBook() {
        when(bookService.findById(1L)).thenReturn(bookDto);

        ResponseEntity<BookDto> response = bookController.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bookDto, response.getBody());
    }

    @Test
    void getAll_ShouldReturnList() {
        when(bookService.findAll()).thenReturn(List.of(bookDto));

        ResponseEntity<List<BookDto>> response = bookController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void delete_ShouldReturnNoContent() {
        ResponseEntity<Void> response = bookController.delete(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(bookService).delete(1L);
    }
}
