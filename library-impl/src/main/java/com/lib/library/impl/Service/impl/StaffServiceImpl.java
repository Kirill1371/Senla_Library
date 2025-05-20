package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.StaffDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.db.entity.Tenant;
import com.lib.library.impl.Repository.TenantRepository;
import com.lib.library.impl.mapper.StaffMapper;
import com.lib.library.impl.Repository.StaffRepository;
import com.lib.library.impl.Service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository repository;
    private final StaffMapper mapper;
    private final TenantRepository tenantRepository; // добавьте этот репозиторий
    private final PasswordEncoder passwordEncoder;

    @Override
    public StaffDto create(StaffDto dto) {
        Staff staff = mapper.toEntity(dto);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        staff.setPassword(encodedPassword);
        // Находим tenant по ID и устанавливаем его для staff
        Tenant tenant = tenantRepository.findById(dto.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + dto.getTenantId()));
        staff.setTenant(tenant);

        return mapper.toDto(repository.save(staff));
    }

    @Override
    public StaffDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<StaffDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StaffDto update(Long id, StaffDto staffDto) {
        Staff entity = repository.findById(id).orElseThrow();
        String encodedPassword = passwordEncoder.encode(staffDto.getPassword());
        staffDto.setPassword(encodedPassword);
        mapper.updateStaffFromDto(staffDto, entity);
        return mapper.toDto(repository.update(entity));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Don`t found" + id));
        repository.deleteById(id);
    }
}