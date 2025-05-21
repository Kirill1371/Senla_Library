package com.lib.library.impl.repository.impl;

import com.lib.library.impl.repository.AuthorRepository;
import com.lib.library.db.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(em::remove);
    }

    @Transactional
    @Override
    public Author update(Author author) {
        String sql = """
            UPDATE authors
            SET name = ?, birth_day = ?, country = ?, staff_id = ?
            WHERE id = ?
        """;
        jdbcTemplate.update(sql,
                author.getName(),
                author.getBirthDay(),
                author.getCountry(),
                author.getStaff().getId(),
                author.getId()
        );
        return author;
    }
}