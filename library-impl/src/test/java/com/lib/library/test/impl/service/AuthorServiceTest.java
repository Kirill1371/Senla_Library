package com.lib.library.test.impl.service;

import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Author;
import com.lib.library.impl.Repository.AuthorRepository;
import com.lib.library.impl.Repository.StaffRepository;
import com.lib.library.impl.Service.impl.AuthorServiceImpl;
import com.lib.library.impl.mapper.AuthorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private AutoCloseable closeable;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        AuthorDto dto = new AuthorDto();
        Author author = new Author();
        Author saved = new Author();
        AuthorDto resultDto = new AuthorDto();

        when(authorMapper.toEntity(dto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(saved);
        when(authorMapper.toDto(saved)).thenReturn(resultDto);

        AuthorDto result = authorService.create(dto);

        assertNotNull(result);
        verify(authorRepository).save(author);
    }

    @Test
    void testGetById() {
        Author author = new Author();
        AuthorDto dto = new AuthorDto();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(dto);

        AuthorDto result = authorService.getById(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());
        assertNull(authorService.getById(1L));
    }

    @Test
    void testGetAll() {
        List<Author> authors = List.of(new Author(), new Author());
        List<AuthorDto> dtos = List.of(new AuthorDto(), new AuthorDto());

        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toDto(any())).thenReturn(new AuthorDto());

        List<AuthorDto> result = authorService.getAll();

        assertEquals(dtos.size(), result.size());
    }

    @Test
    void testUpdate() {
        AuthorDto dto = new AuthorDto();
        Author existing = new Author();
        Author updated = new Author();
        AuthorDto resultDto = new AuthorDto();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existing));
        doNothing().when(authorMapper).updateAuthorFromDto(dto, existing);
        when(authorRepository.update(existing)).thenReturn(updated);
        when(authorMapper.toDto(updated)).thenReturn(resultDto);

        AuthorDto result = authorService.update(1L, dto);

        assertNotNull(result);
        verify(authorMapper).updateAuthorFromDto(dto, existing);
    }

    @Test
    void testDelete() {
        doNothing().when(authorRepository).deleteById(1L);
        authorService.delete(1L);
        verify(authorRepository).deleteById(1L);
    }
}
