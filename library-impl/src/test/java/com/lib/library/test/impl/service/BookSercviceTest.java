package com.lib.library.test.impl.service;

import com.lib.library.api.dto.BookDto;
import com.lib.library.db.entity.Book;
import com.lib.library.impl.repository.BookRepository;
import com.lib.library.impl.service.impl.BookServiceImpl;
import com.lib.library.impl.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private final BookDto sampleDto = new BookDto(1L, "Sample", 1L, 2L, 1929, true);
    private final Book sampleEntity = new Book();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedDto() {
        when(bookMapper.toEntity(sampleDto)).thenReturn(sampleEntity);
        when(bookRepository.save(sampleEntity)).thenReturn(sampleEntity);
        when(bookMapper.toDto(sampleEntity)).thenReturn(sampleDto);

        BookDto result = bookService.save(sampleDto);

        assertEquals(sampleDto, result);
        verify(bookRepository).save(sampleEntity);
    }

    @Test
    void findById_WhenExists_ShouldReturnDto() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleEntity));
        when(bookMapper.toDto(sampleEntity)).thenReturn(sampleDto);

        BookDto result = bookService.findById(1L);

        assertEquals(sampleDto, result);
    }

    @Test
    void findById_WhenNotFound_ShouldThrow() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.findById(1L));
    }

    @Test
    void delete_WhenExists_ShouldDelete() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleEntity));

        bookService.delete(1L);

        verify(bookRepository).deleteById(1L);
    }
}
