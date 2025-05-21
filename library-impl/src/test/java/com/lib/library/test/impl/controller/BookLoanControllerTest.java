package com.lib.library.test.impl.controller;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.impl.Service.BookLoanService;
import com.lib.library.impl.Controller.BookLoanControllerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookLoanControllerImplTest {

    private BookLoanService service;
    private BookLoanControllerImpl controller;

    @BeforeEach
    void setUp() {
        service = mock(BookLoanService.class);
        controller = new BookLoanControllerImpl(service);
    }

    @Test
    void testCreate() {
        BookLoanDto dto = new BookLoanDto();
        BookLoanDto expected = new BookLoanDto();
        when(service.create(dto)).thenReturn(expected);

        BookLoanDto result = controller.create(dto);
        assertEquals(expected, result);
    }

    @Test
    void testGetById() {
        BookLoanDto expected = new BookLoanDto();
        when(service.getById(1L)).thenReturn(expected);

        BookLoanDto result = controller.getById(1L);
        assertEquals(expected, result);
    }

    @Test
    void testGetAll() {
        when(service.getAll()).thenReturn(Arrays.asList(new BookLoanDto(), new BookLoanDto()));
        List<BookLoanDto> result = controller.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        BookLoanDto dto = new BookLoanDto();
        BookLoanDto updated = new BookLoanDto();

        when(service.update(1L, dto)).thenReturn(updated);

        BookLoanDto result = controller.update(1L, dto);
        assertEquals(updated, result);
    }

    @Test
    void testDelete() {
        doNothing().when(service).delete(1L);
        controller.delete(1L);
        verify(service).delete(1L);
    }
}
