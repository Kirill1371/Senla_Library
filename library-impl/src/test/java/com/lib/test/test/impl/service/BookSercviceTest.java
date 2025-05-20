//package com.lib.library.test.impl.service;
//
//import com.lib.library.api.dto.BookDto;
////import com.lib.library.db.*;
//import com.lib.library.impl.Repository.BookRepository;
//import com.lib.library.impl.Service.impl.BookServiceImpl;
//import com.lib.library.impl.mapper.BookMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import jakarta.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private BookMapper bookMapper;
//
//    @InjectMocks
//    private BookServiceImpl bookService;
//
//    @Test
//    void save_ShouldReturnSavedBookDto() {
//        // Arrange
//        BookDto inputDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//        Book entity = new Book();
//        Book savedEntity = new Book();
//        BookDto expectedDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//
//        when(bookMapper.toEntity(inputDto)).thenReturn(entity);
//        when(bookRepository.save(entity)).thenReturn(savedEntity);
//        when(bookMapper.toDto(savedEntity)).thenReturn(expectedDto);
//
//        // Act
//        BookDto result = bookService.save(inputDto);
//
//        // Assert
//        assertSame(expectedDto, result);
//        verify(bookMapper).toEntity(inputDto);
//        verify(bookRepository).save(entity);
//        verify(bookMapper).toDto(savedEntity);
//    }
//
//    @Test
//    void findById_WhenBookExists_ShouldReturnBookDto() {
//        // Arrange
//        Long id = 1L;
//        Book entity = new Book();
//        BookDto expectedDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//
//        when(bookRepository.findById(id)).thenReturn(Optional.of(entity));
//        when(bookMapper.toDto(entity)).thenReturn(expectedDto);
//
//        // Act
//        BookDto result = bookService.findById(id);
//
//        // Assert
//        assertSame(expectedDto, result);
//        verify(bookRepository).findById(id);
//        verify(bookMapper).toDto(entity);
//    }
//
//    @Test
//    void findById_WhenBookNotExists_ShouldThrowException() {
//        // Arrange
//        Long id = 1L;
//        when(bookRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> bookService.findById(id));
//        verify(bookRepository).findById(id);
//        verify(bookMapper, never()).toDto(any());
//    }
//
//    @Test
//    void findAll_ShouldReturnListOfBookDtos() {
//        // Arrange
//        Book entity1 = new Book();
//        Book entity2 = new Book();
//        BookDto dto1 = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//        BookDto dto2 = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//
//        when(bookRepository.findAll()).thenReturn(List.of(entity1, entity2));
//        when(bookMapper.toDto(entity1)).thenReturn(dto1);
//        when(bookMapper.toDto(entity2)).thenReturn(dto2);
//
//        // Act
//        List<BookDto> result = bookService.findAll();
//
//        // Assert
//        assertEquals(2, result.size());
//        assertSame(dto1, result.get(0));
//        assertSame(dto2, result.get(1));
//        verify(bookRepository).findAll();
//        verify(bookMapper, times(2)).toDto(any());
//    }
//
//    @Test
//    void update_WhenBookExists_ShouldReturnUpdatedBookDto() {
//        // Arrange
//        Long id = 1L;
//        BookDto inputDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//        Book existingEntity = new Book();
//        Book updatedEntity = new Book();
//        BookDto expectedDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//
//        when(bookRepository.findById(id)).thenReturn(Optional.of(existingEntity));
//        doNothing().when(bookMapper).updateEntityFromDto(inputDto, existingEntity);
//        when(bookRepository.update(existingEntity)).thenReturn(updatedEntity);
//        when(bookMapper.toDto(updatedEntity)).thenReturn(expectedDto);
//
//        // Act
//        BookDto result = bookService.update(id, inputDto);
//
//        // Assert
//        assertSame(expectedDto, result);
//        verify(bookRepository).findById(id);
//        verify(bookMapper).updateEntityFromDto(inputDto, existingEntity);
//        verify(bookRepository).update(existingEntity);
//        verify(bookMapper).toDto(updatedEntity);
//    }
//
//    @Test
//    void update_WhenBookNotExists_ShouldThrowException() {
//        // Arrange
//        Long id = 1L;
//        BookDto inputDto = new BookDto(1L, "Test Book", 1L, 1L, 2023, true);
//        when(bookRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> bookService.update(id, inputDto));
//        verify(bookRepository).findById(id);
//        verify(bookMapper, never()).updateEntityFromDto(any(), any());
//        verify(bookRepository, never()).update(any());
//        verify(bookMapper, never()).toDto(any());
//    }
//
//    @Test
//    void delete_WhenBookExists_ShouldDeleteBook() {
//        // Arrange
//        Long id = 1L;
//        Book entity = new Book();
//        when(bookRepository.findById(id)).thenReturn(Optional.of(entity));
//        doNothing().when(bookRepository).deleteById(id);
//
//        // Act
//        bookService.delete(id);
//
//        // Assert
//        verify(bookRepository).findById(id);
//        verify(bookRepository).deleteById(id);
//    }
//
//    @Test
//    void delete_WhenBookNotExists_ShouldThrowException() {
//        // Arrange
//        Long id = 1L;
//        when(bookRepository.findById(id)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(EntityNotFoundException.class, () -> bookService.delete(id));
//        verify(bookRepository).findById(id);
//        verify(bookRepository, never()).deleteById(any());
//    }
//}


package com.lib.test.test.impl.service;

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
