package com.lib.library.impl.repository.impl;

import com.lib.library.db.entity.Reader;
import com.lib.library.impl.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReaderRepositoryImpl implements ReaderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Reader> findByName(String name) {
        String sql = "SELECT id, name, password FROM reader WHERE name = ?";
        return jdbcTemplate.query(sql, ps -> ps.setString(1, name), rs -> {
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        });
    }


    @Override
    public Optional<Reader> findByNameAndPassword(String name, String password) {
        String sql = "SELECT id, name, password FROM reader WHERE name = ? AND password = ?";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, name);
            ps.setString(2, password);
        }, rs -> {
            if (rs.next()) {
                Reader reader = mapRow(rs);
                return Optional.of(reader);
            }
            return Optional.empty();
        });
    }

    @Override
    public Reader save(Reader reader) {
        String sql = "INSERT INTO reader (name, password) VALUES (?, ?) RETURNING id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, reader.getName());
            ps.setString(2, reader.getPassword());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            reader.setId(key.longValue());
        }

        return reader;
    }

    @Override
    public Optional<Reader> findById(Long id) {
        String sql = "SELECT id, name, password FROM reader WHERE id = ?";

        return jdbcTemplate.query(sql, ps -> ps.setLong(1, id), rs -> {
            if (rs.next()) {
                Reader reader = mapRow(rs);
                return Optional.of(reader);
            }
            return Optional.empty();
        });
    }

    @Override
    public List<Reader> findAll() {
        String sql = "SELECT id, name, password FROM reader";

        return jdbcTemplate.query(connection -> connection.prepareStatement(sql), rs -> {
            List<Reader> result = new java.util.ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        });
    }

    @Override
    public Reader update(Reader reader) {
        String sql = "UPDATE reader SET name = ?, password = ? WHERE id = ?";

        jdbcTemplate.update(sql, reader.getName(), reader.getPassword(), reader.getId());

        return reader;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reader WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Reader mapRow(ResultSet rs) throws SQLException {
        Reader reader = new Reader();
        reader.setId(rs.getLong("id"));
        reader.setName(rs.getString("name"));
        reader.setPassword(rs.getString("password"));
        return reader;
    }
}
