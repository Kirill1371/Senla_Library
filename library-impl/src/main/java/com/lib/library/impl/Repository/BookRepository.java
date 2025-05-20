package com.lib.library.impl.Repository;

import com.lib.library.db.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    Book update(Book book);
    void deleteById(Long id);
}