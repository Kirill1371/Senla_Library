package com.lib.library.impl.repository;

import com.lib.library.db.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {
    Staff save(Staff staff);
    Optional<Staff> findById(Long id);
    Optional<Staff> findByEmail(String email);
    List<Staff> findAll();
    Staff update(Staff staff);
    void deleteById(Long id);
}