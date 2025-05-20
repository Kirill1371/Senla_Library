package com.lib.library.impl.repository.impl;

import com.lib.library.db.entity.Category;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final DataSource dataSource;

    @Override
    public Category save(Category category) {
        String sql = "INSERT INTO categories (name, parent_id, staff_id) VALUES (?, ?, ?) RETURNING id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, category.getName());

            if (category.getParentCategory() != null) {
                ps.setLong(2, category.getParentCategory().getId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }

            ps.setLong(3, category.getStaff().getId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category.setId(rs.getLong("id"));
                }
            }

            return category;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving category", e);
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        String sql = "SELECT id, name, parent_id, staff_id FROM categories WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = mapRow(rs);
                    return Optional.of(category);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding category by id", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT id, name, parent_id, staff_id FROM categories";
        List<Category> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all categories", e);
        }

        return result;
    }

    @Override
    public Category update(Category category) {
        String sql = "UPDATE categories SET name = ?, parent_id = ?, staff_id = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, category.getName());

            if (category.getParentCategory() != null) {
                ps.setLong(2, category.getParentCategory().getId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }

            ps.setLong(3, category.getStaff().getId());
            ps.setLong(4, category.getId());

            ps.executeUpdate();

            return category;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category", e);
        }
    }

    @Override
    public List<Category> findByParentCategoryId(Long parentId) {
        String sql = "SELECT id, name, parent_id, staff_id FROM categories WHERE parent_id = ?";
        List<Category> result = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, parentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding subcategories", e);
        }

        return result;
    }

    private Category mapRow(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));

        long parentId = rs.getLong("parent_id");
        if (!rs.wasNull()) {
            Category parent = new Category();
            parent.setId(parentId);
            category.setParentCategory(parent);
        }

        Staff staff = new Staff();
        staff.setId(rs.getLong("staff_id"));
        category.setStaff(staff);

        return category;
    }
}
