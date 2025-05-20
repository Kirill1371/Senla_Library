package com.lib.library.impl.repository;

import com.lib.library.db.entity.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantRepository {
    Tenant save(Tenant tenant);
    Optional<Tenant> findById(Long id);
    List<Tenant> findAll();
    Tenant update(Tenant tenant);
    void deleteById(Long id);
}
