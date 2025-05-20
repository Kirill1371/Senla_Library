package com.lib.library.impl.repository;

import com.lib.library.db.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository {
    Optional<Reader> findByName(String name);
    Optional<Reader> findByNameAndPassword(String name, String password);
    Reader save(Reader reader);
    Optional<Reader> findById(Long id);
    List<Reader> findAll();
    Reader update(Reader reader);
    void deleteById(Long id);
}
