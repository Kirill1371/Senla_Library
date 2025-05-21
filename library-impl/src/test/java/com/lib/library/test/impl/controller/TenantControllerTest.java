package com.lib.library.test.impl.controller;
import com.lib.library.api.dto.TenantDto;
import com.lib.library.impl.controller.TenantControllerImpl;
import com.lib.library.impl.service.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TenantControllerTest {

    private TenantService service;
    private TenantControllerImpl controller;

    @BeforeEach
    void setUp() {
        service = mock(TenantService.class);
        controller = new TenantControllerImpl(service);
    }

    @Test
    void testCreate() {
        TenantDto input = new TenantDto();
        TenantDto expected = new TenantDto();
        when(service.create(input)).thenReturn(expected);

        TenantDto result = controller.create(input);
        assertEquals(expected, result);
    }

    @Test
    void testGetById() {
        TenantDto dto = new TenantDto();
        when(service.getById(1L)).thenReturn(dto);

        TenantDto result = controller.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetAll() {
        List<TenantDto> tenants = Arrays.asList(new TenantDto(), new TenantDto());
        when(service.getAll()).thenReturn(tenants);

        List<TenantDto> result = controller.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        TenantDto dto = new TenantDto();
        TenantDto updated = new TenantDto();
        when(service.update(1L, dto)).thenReturn(updated);

        TenantDto result = controller.update(1L, dto);
        assertEquals(updated, result);
    }

    @Test
    void testDelete() {
        doNothing().when(service).delete(1L);
        controller.delete(1L);
        verify(service).delete(1L);
    }
}
