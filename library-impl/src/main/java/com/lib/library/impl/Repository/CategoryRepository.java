//package com.lib.library.impl.Repository;
//
//import com.lib.library.db.entity.Category;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface CategoryRepository extends JpaRepository<Category, Long> {
//}


package com.lib.library.impl.Repository;

import com.lib.library.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentCategoryId(Long parentId);
}
