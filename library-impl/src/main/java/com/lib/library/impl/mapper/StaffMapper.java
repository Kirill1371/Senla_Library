package com.lib.library.impl.mapper;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDto toDto(Staff staff);
    Staff toEntity(StaffDto dto);
}
