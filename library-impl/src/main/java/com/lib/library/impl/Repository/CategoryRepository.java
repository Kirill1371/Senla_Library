package com.lib.library.impl.Repository;

import com.lib.library.db.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    Category update(Category category);

    void deleteById(Long id);

    List<Category> findByParentCategoryId(Long parentId);
}