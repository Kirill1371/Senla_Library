package com.lib.test.test.impl.service;

import com.lib.library.api.dto.TenantDto;
import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.repository.TenantRepository;
import com.lib.library.impl.service.impl.TenantServiceImpl;
import com.lib.library.impl.mapper.TenantMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TenantServiceTest {

    private TenantRepository repository;
    private TenantMapper mapper;
    private TenantServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(TenantRepository.class);
        mapper = mock(TenantMapper.class);
        service = new TenantServiceImpl(repository, mapper);
    }

    @Test
    void testCreate() {
        TenantDto dto = new TenantDto();
        Tenant entity = new Tenant();
        Tenant savedEntity = new Tenant();
        TenantDto expectedDto = new TenantDto();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(expectedDto);

        TenantDto result = service.create(dto);
        assertEquals(expectedDto, result);
    }

    @Test
    void testGetByIdFound() {
        Tenant entity = new Tenant();
        TenantDto dto = new TenantDto();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        TenantDto result = service.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        TenantDto result = service.getById(1L);
        assertNull(result);
    }

    @Test
    void testGetAll() {
        List<Tenant> entities = Arrays.asList(new Tenant(), new Tenant());
        List<TenantDto> dtos = Arrays.asList(new TenantDto(), new TenantDto());

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDto(any())).thenReturn(new TenantDto());

        List<TenantDto> result = service.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        TenantDto dto = new TenantDto();
        dto.setName("Updated Name");

        Tenant entity = new Tenant();
        Tenant updatedEntity = new Tenant();
        updatedEntity.setName("Updated Name");

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.update(entity)).thenReturn(updatedEntity);
        when(mapper.toDto(updatedEntity)).thenReturn(dto);

        TenantDto result = service.update(1L, dto);
        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository).deleteById(1L);
    }
}
