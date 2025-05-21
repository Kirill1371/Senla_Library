package com.lib.library.impl.controller;

import com.lib.library.api.controller.BookController;
import com.lib.library.api.dto.BookDto;
import com.lib.library.impl.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    public ResponseEntity<BookDto> create(BookDto bookDto) {
        return ResponseEntity.ok(bookService.save(bookDto));
    }

    @Override
    public ResponseEntity<BookDto> getById(Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Override
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Override
    public ResponseEntity<BookDto> update(Long id, BookDto bookDto) {
        return ResponseEntity.ok(bookService.update(id, bookDto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}