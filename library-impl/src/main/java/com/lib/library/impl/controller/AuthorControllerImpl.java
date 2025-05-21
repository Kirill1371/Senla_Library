package com.lib.library.impl.controller;

import com.lib.library.api.controller.AuthorController;
import com.lib.library.api.dto.AuthorDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.impl.service.AuthorService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService service;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public AuthorDto create(AuthorDto dto) {
        setCurrentStaffId(dto);
        System.out.println("Received DTO: " + dto);
        return service.create(dto);
    }

    @Override
    public AuthorDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public List<AuthorDto> getAll() {
        return service.getAll();
    }

    @Override
    public AuthorDto update(Long id, AuthorDto dto) {
        setCurrentStaffId(dto);
        System.out.println("Received DTO: " + dto);
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    private void setCurrentStaffId(AuthorDto authorDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Staff staff = userDetailsService.getStaffByEmail(username);
        authorDto.setStaffId(staff.getId());
    }
}