package com.lib.library.impl.mapper;

import com.lib.library.api.dto.TenantDto;
import com.lib.library.db.entity.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    @Mapping(target = "id", source = "tenant.id")
    TenantDto toDto(Tenant tenant);

    @Mapping(target = "id", ignore = true)
    Tenant toEntity(TenantDto dto);
}
