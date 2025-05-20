package com.lib.test.test.impl.controller;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.impl.controller.StaffControllerImpl;
import com.lib.library.impl.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffControllerTest {

    private StaffService staffService;
    private StaffControllerImpl controller;

    @BeforeEach
    void setUp() {
        staffService = mock(StaffService.class);
        controller = new StaffControllerImpl(staffService);
    }

    @Test
    void testCreate() {
        StaffDto input = new StaffDto();
        StaffDto expected = new StaffDto();

        when(staffService.create(input)).thenReturn(expected);

        StaffDto result = controller.create(input);
        assertEquals(expected, result);
    }

    @Test
    void testGetById() {
        StaffDto dto = new StaffDto();
        when(staffService.getById(1L)).thenReturn(dto);

        StaffDto result = controller.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetAll() {
        List<StaffDto> list = Arrays.asList(new StaffDto(), new StaffDto());
        when(staffService.getAll()).thenReturn(list);

        List<StaffDto> result = controller.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        StaffDto dto = new StaffDto();
        StaffDto updated = new StaffDto();
        when(staffService.update(1L, dto)).thenReturn(updated);

        StaffDto result = controller.update(1L, dto);
        assertEquals(updated, result);
    }

    @Test
    void testDelete() {
        doNothing().when(staffService).delete(1L);
        controller.delete(1L);
        verify(staffService).delete(1L);
    }
}
