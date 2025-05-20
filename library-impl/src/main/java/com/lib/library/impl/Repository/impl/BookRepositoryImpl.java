package com.lib.library.impl.Repository.impl;

import com.lib.library.db.entity.Author;
import com.lib.library.db.entity.Book;
import com.lib.library.db.entity.Category;
import com.lib.library.impl.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Book save(Book book) {
        String sql = "INSERT INTO books (title, author_id, category_id, publish_year, available) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        Long id = jdbcTemplate.queryForObject(sql, Long.class,
                book.getTitle(),
                book.getAuthor().getId(),
                book.getCategory().getId(),
                book.getPublishYear(),
                Boolean.TRUE.equals(book.getAvailable())
        );

        book.setId(id);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = """
            SELECT b.id, b.title, b.publish_year, b.available,
                   a.id AS author_id, a.name AS author_name,
                   c.id AS category_id, c.name AS category_name
            FROM books b
            JOIN authors a ON b.author_id = a.id
            JOIN categories c ON b.category_id = c.id
            WHERE b.id = ?
        """;

        try {
            Book book = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                    Book.builder()
                            .id(rs.getLong("id"))
                            .title(rs.getString("title"))
                            .publishYear(rs.getInt("publish_year"))
                            .available(rs.getBoolean("available"))
                            .author(Author.builder()
                                    .id(rs.getLong("author_id"))
                                    .name(rs.getString("author_name"))
                                    .build())
                            .category(Category.builder()
                                    .id(rs.getLong("category_id"))
                                    .name(rs.getString("category_name"))
                                    .build())
                            .build()
            );
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        String sql = """
            SELECT b.id, b.title, b.publish_year, b.available,
                   a.id AS author_id, a.name AS author_name,
                   c.id AS category_id, c.name AS category_name
            FROM books b
            JOIN authors a ON b.author_id = a.id
            JOIN categories c ON b.category_id = c.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                Book.builder()
                        .id(rs.getLong("id"))
                        .title(rs.getString("title"))
                        .publishYear(rs.getInt("publish_year"))
                        .available(rs.getBoolean("available"))
                        .author(Author.builder()
                                .id(rs.getLong("author_id"))
                                .name(rs.getString("author_name"))
                                .build())
                        .category(Category.builder()
                                .id(rs.getLong("category_id"))
                                .name(rs.getString("category_name"))
                                .build())
                        .build()
        );
    }

    @Override
    public Book update(Book book) {
        String sql = """
            UPDATE books
            SET title = ?, author_id = ?, category_id = ?, publish_year = ?, available = ?
            WHERE id = ?
        """;
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor().getId(),
                book.getCategory().getId(),
                book.getPublishYear(),
                Boolean.TRUE.equals(book.getAvailable()),
                book.getId()
        );
        return book;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}