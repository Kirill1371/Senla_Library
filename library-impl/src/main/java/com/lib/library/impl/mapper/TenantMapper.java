package com.lib.library.impl.mapper;

import com.lib.library.api.dto.TenantDto;
import com.lib.library.db.entity.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantDto toDto(Tenant tenant);
    Tenant toEntity(TenantDto dto);
}
