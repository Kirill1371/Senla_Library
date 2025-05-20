package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.BookDto;
import com.lib.library.db.entity.Book;
import com.lib.library.impl.mapper.BookMapper;
import com.lib.library.impl.Repository.BookRepository;
import com.lib.library.impl.Service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Valid
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        bookMapper.updateEntityFromDto(bookDto, existingBook);

        Book updatedBook = bookRepository.update(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found" + id));
        bookRepository.deleteById(id);
    }
}