package com.lib.library.impl.repository.impl;

import com.lib.library.db.entity.Staff;
import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StaffRepositoryImpl implements StaffRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Staff save(Staff staff) {
        String sql = "INSERT INTO staff (name, password, email, role, tenant_id) VALUES (?, ?, ?, ?, ?) RETURNING ID";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, staff.getName());
            ps.setString(2, staff.getPassword());
            ps.setString(3, staff.getEmail());
            ps.setString(4, staff.getRole());
            ps.setLong(5, staff.getTenant().getId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            staff.setId(keyHolder.getKey().longValue());
        }

        return staff;
    }

    @Override
    public Optional<Staff> findById(Long id) {
        String sql = "SELECT * FROM staff WHERE id = ?";
        try {
            Staff staff = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(staff);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Staff> findByEmail(String email) {
        String sql = "SELECT * FROM staff WHERE email = ?";
        try {
            Staff staff = jdbcTemplate.queryForObject(sql, rowMapper, email);
            return Optional.ofNullable(staff);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Staff> findAll() {
        String sql = "SELECT * FROM staff";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Staff update(Staff staff) {
        String sql = "UPDATE staff SET name = ?, password = ?, email = ?, role = ?, tenant_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                staff.getName(),
                staff.getPassword(),
                staff.getEmail(),
                staff.getRole(),
                staff.getTenant().getId(),
                staff.getId());
        return staff;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM staff WHERE id = ?", id);
    }

    private final RowMapper<Staff> rowMapper = (rs, rowNum) -> Staff.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .email(rs.getString("email"))
            .role(rs.getString("role"))
            .tenant(Tenant.builder().id(rs.getLong("tenant_id")).build())
            .build();
    }
