package com.lib.library.test.impl.service;

import com.lib.library.api.dto.BookDto;
import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.db.entity.BookLoan;
import com.lib.library.impl.Repository.BookLoanRepository;
import com.lib.library.impl.Service.impl.BookLoanServiceImpl;
import com.lib.library.impl.Service.impl.BookServiceImpl;
import com.lib.library.impl.mapper.BookLoanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookLoanServiceTest {

    private BookLoanRepository repository;
    private BookLoanMapper mapper;
    private BookServiceImpl bookService;
    private BookLoanServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(BookLoanRepository.class);
        mapper = mock(BookLoanMapper.class);
        bookService = mock(BookServiceImpl.class);
        service = new BookLoanServiceImpl(repository, mapper, bookService);
    }

    @Test
    void testCreate() {
        BookLoanDto dto = new BookLoanDto();
        dto.setBookId(1L);
        BookLoan loan = new BookLoan();
        BookLoan savedLoan = new BookLoan();
        BookLoanDto resultDto = new BookLoanDto();

        when(mapper.toEntity(dto)).thenReturn(loan);
        when(repository.save(loan)).thenReturn(savedLoan);
        when(mapper.toDto(savedLoan)).thenReturn(resultDto);
        when(bookService.findById(1L)).thenReturn(new BookDto(100L, "Test", 2L, 3L, 2025, true));

        BookLoanDto result = service.create(dto);

        assertNotNull(result);
        verify(bookService).update(anyLong(), any(BookDto.class));
    }

    @Test
    void testGetById() {
        BookLoan loan = new BookLoan();
        BookLoanDto dto = new BookLoanDto();

        when(repository.findById(1L)).thenReturn(Optional.of(loan));
        when(mapper.toDto(loan)).thenReturn(dto);

        BookLoanDto result = service.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new BookLoan()));
        when(mapper.toDto(any())).thenReturn(new BookLoanDto());

        List<BookLoanDto> result = service.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void testUpdate_withReturnDate() {
        BookLoanDto dto = new BookLoanDto();
        dto.setReturnDate(LocalDate.now());
        BookLoan loan = new BookLoan();
        loan.setBook(new com.lib.library.db.entity.Book());
        loan.getBook().setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(loan));
        doNothing().when(mapper).updateBookLoanFromDto(eq(dto), any(BookLoan.class));
        when(repository.update(loan)).thenReturn(loan);
        when(mapper.toDto(loan)).thenReturn(new BookLoanDto());
        when(bookService.findById(1L)).thenReturn(new BookDto(1L, "Test2", 2L, 3L, 2024, true));

        BookLoanDto result = service.update(1L, dto);
        assertNotNull(result);
        verify(bookService).update(eq(1L), any(BookDto.class));
    }

    @Test
    void testDelete_withReturn() {
        BookLoan loan = new BookLoan();
        loan.setBook(new com.lib.library.db.entity.Book());
        loan.getBook().setId(1L);
        loan.setReturnDate(null);

        when(repository.findById(1L)).thenReturn(Optional.of(loan));
        when(bookService.findById(1L)).thenReturn(new BookDto(1L, "Test2", 2L, 3L, 2024, true));

        service.delete(1L);

        verify(bookService).update(eq(1L), any(BookDto.class));
        verify(repository).deleteById(1L);
    }
}
