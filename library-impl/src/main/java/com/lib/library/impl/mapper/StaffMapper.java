package com.lib.library.impl.mapper;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
//public interface StaffMapper {
//    StaffDto toDto(Staff staff);
//    Staff toEntity(StaffDto dto);
//}

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "tenantId", source = "tenant.id") // Добавьте это
    StaffDto toDto(Staff staff);

    @Mapping(target = "tenant", ignore = true)
    Staff toEntity(StaffDto dto);
}
