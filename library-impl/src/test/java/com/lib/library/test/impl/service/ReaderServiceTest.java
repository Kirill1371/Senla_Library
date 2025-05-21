package com.lib.library.test.impl.service;

import com.lib.library.api.dto.ReaderDto;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.Repository.ReaderRepository;
import com.lib.library.impl.Service.impl.ReaderServiceImpl;
import com.lib.library.impl.mapper.ReaderMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReaderServiceImplTest {

    @InjectMocks
    private ReaderServiceImpl service;

    @Mock
    private ReaderRepository repository;

    @Mock
    private ReaderMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Reader reader;
    private ReaderDto readerDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reader = new Reader();
        reader.setId(1L);
        reader.setName("John");
        reader.setPassword("encoded");

        readerDto = new ReaderDto();
        readerDto.setId(1L);
        readerDto.setName("John");
        readerDto.setPassword("plain");
    }

    @Test
    void testCreate() {
        when(mapper.toEntity(readerDto)).thenReturn(reader);
        when(passwordEncoder.encode("plain")).thenReturn("encoded");
        when(repository.save(reader)).thenReturn(reader);
        when(mapper.toDto(reader)).thenReturn(readerDto);

        ReaderDto result = service.create(readerDto);

        assertEquals(readerDto, result);
        verify(passwordEncoder).encode("plain");
        verify(repository).save(reader);
    }

    @Test
    void testGetById_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(reader));
        when(mapper.toDto(reader)).thenReturn(readerDto);

        ReaderDto result = service.getById(1L);

        assertEquals(readerDto, result);
    }

    @Test
    void testGetById_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.getById(1L));
    }

    @Test
    void testGetAll() {
        List<Reader> readers = List.of(reader);
        List<ReaderDto> dtos = List.of(readerDto);

        when(repository.findAll()).thenReturn(readers);
        when(mapper.toDto(reader)).thenReturn(readerDto);

        List<ReaderDto> result = service.getAll();

        assertEquals(dtos, result);
    }

    @Test
    void testUpdate_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(reader));
        when(repository.update(reader)).thenReturn(reader);
        when(mapper.toDto(reader)).thenReturn(readerDto);

        ReaderDto updateDto = new ReaderDto();
        updateDto.setName("Updated");
        updateDto.setPassword("new");

        ReaderDto result = service.update(1L, updateDto);

        assertEquals(readerDto, result);
        assertEquals("Updated", reader.getName());
        assertEquals("new", reader.getPassword());
    }

    @Test
    void testUpdate_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.update(1L, new ReaderDto()));
    }

    @Test
    void testDelete_found() {
        when(repository.findById(1L)).thenReturn(Optional.of(reader));

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDelete_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
    }
}
