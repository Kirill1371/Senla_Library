package com.lib.library.impl.repository.impl;

import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TenantRepositoryImpl implements TenantRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Tenant save(Tenant tenant) {
        String sql = "INSERT INTO tenant (name) VALUES (?) RETURNING id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tenant.getName());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            tenant.setId(key.longValue());
        }

        return tenant;
    }

    @Override
    public Optional<Tenant> findById(Long id) {
        String sql = "SELECT id, name FROM tenant WHERE id = ?";

        List<Tenant> tenants = jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs), id);
        return tenants.stream().findFirst();
    }


    @Override
    public List<Tenant> findAll() {
        String sql = "SELECT id, name FROM tenant";

        return jdbcTemplate.query(connection -> connection.prepareStatement(sql), rs -> {
            List<Tenant> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        });
    }

    @Override
    public Tenant update(Tenant tenant) {
        String sql = "UPDATE tenant SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, tenant.getName(), tenant.getId());
        return tenant;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM tenant WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Tenant mapRow(ResultSet rs) throws SQLException {
        Tenant tenant = new Tenant();
        tenant.setId(rs.getLong("id"));
        tenant.setName(rs.getString("name"));
        return tenant;
    }
}
