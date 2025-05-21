package com.lib.library.test.impl.service;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.repository.StaffRepository;
import com.lib.library.impl.repository.TenantRepository;
import com.lib.library.impl.service.impl.StaffServiceImpl;
import com.lib.library.impl.mapper.StaffMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StaffServiceTest {

    private StaffRepository staffRepository;
    private TenantRepository tenantRepository;
    private StaffMapper staffMapper;
    private PasswordEncoder passwordEncoder;
    private StaffServiceImpl staffService;

    @BeforeEach
    void setUp() {
        staffRepository = mock(StaffRepository.class);
        tenantRepository = mock(TenantRepository.class);
        staffMapper = mock(StaffMapper.class);
        passwordEncoder = mock(PasswordEncoder.class);
        staffService = new StaffServiceImpl(staffRepository, staffMapper, tenantRepository, passwordEncoder);
    }

    @Test
    void testCreate() {
        StaffDto dto = new StaffDto();
        dto.setPassword("plain");
        dto.setTenantId(1L);

        Staff entity = new Staff();
        Tenant tenant = new Tenant();
        Staff savedEntity = new Staff();
        StaffDto expectedDto = new StaffDto();

        when(staffMapper.toEntity(dto)).thenReturn(entity);
        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        when(tenantRepository.findById(1L)).thenReturn(Optional.of(tenant));
        when(staffRepository.save(entity)).thenReturn(savedEntity);
        when(staffMapper.toDto(savedEntity)).thenReturn(expectedDto);

        StaffDto result = staffService.create(dto);

        assertEquals(expectedDto, result);
        assertEquals("encoded", entity.getPassword());
        assertEquals(tenant, entity.getTenant());
    }

    @Test
    void testGetByIdFound() {
        Staff entity = new Staff();
        StaffDto dto = new StaffDto();
        when(staffRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(staffMapper.toDto(entity)).thenReturn(dto);

        StaffDto result = staffService.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetByIdNotFound() {
        when(staffRepository.findById(1L)).thenReturn(Optional.empty());

        StaffDto result = staffService.getById(1L);
        assertNull(result);
    }

    @Test
    void testGetAll() {
        List<Staff> staffList = Arrays.asList(new Staff(), new Staff());
        when(staffRepository.findAll()).thenReturn(staffList);
        when(staffMapper.toDto(any())).thenReturn(new StaffDto());

        List<StaffDto> result = staffService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdate() {
        StaffDto dto = new StaffDto();
        dto.setPassword("newPassword");

        Staff entity = new Staff();
        Staff updatedEntity = new Staff();
        StaffDto expectedDto = new StaffDto();

        when(staffRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPass");
        doAnswer(invocation -> {
            StaffDto argDto = invocation.getArgument(0);
            Staff argEntity = invocation.getArgument(1);
            argEntity.setPassword(argDto.getPassword());
            return null;
        }).when(staffMapper).updateStaffFromDto(any(), any());
        when(staffRepository.update(entity)).thenReturn(updatedEntity);
        when(staffMapper.toDto(updatedEntity)).thenReturn(expectedDto);

        StaffDto result = staffService.update(1L, dto);
        assertEquals(expectedDto, result);
    }

    @Test
    void testDeleteSuccess() {
        Staff entity = new Staff();
        when(staffRepository.findById(1L)).thenReturn(Optional.of(entity));

        staffService.delete(1L);
        verify(staffRepository).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(staffRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> staffService.delete(99L));
    }
}
