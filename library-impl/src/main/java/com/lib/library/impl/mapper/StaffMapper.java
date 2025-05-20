package com.lib.library.impl.mapper;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "tenantId", source = "tenant.id") // Добавьте это
    StaffDto toDto(Staff staff);

    @Mapping(target = "tenant", ignore = true)
    Staff toEntity(StaffDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "password", source = "password")
    void updateStaffFromDto(StaffDto dto, @MappingTarget Staff entity);
}