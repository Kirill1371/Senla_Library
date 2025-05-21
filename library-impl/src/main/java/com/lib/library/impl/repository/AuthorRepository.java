package com.lib.library.impl.repository;

import com.lib.library.db.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    void deleteById(Long id);
    Author update(Author author);
}