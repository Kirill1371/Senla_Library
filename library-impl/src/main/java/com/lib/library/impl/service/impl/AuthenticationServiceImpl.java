package com.lib.library.impl.service.impl;

import com.lib.library.api.dto.AuthRequestDto;
import com.lib.library.api.dto.AuthResponseDto;
import com.lib.library.api.dto.RegisterRequestDto;
import com.lib.library.db.entity.Staff;
import com.lib.library.db.entity.Tenant;
import com.lib.library.db.enums.StaffRole;
import com.lib.library.impl.repository.ReaderRepository;
import com.lib.library.impl.repository.StaffRepository;
import com.lib.library.impl.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final StaffRepository staffRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final ReaderRepository readerRepository;

    // Staff
    public AuthResponseDto authenticate(AuthRequestDto request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponseDto(token);
    }

    public void register(RegisterRequestDto request) {
        Tenant tenant = new Tenant();
        tenant.setId(request.getTenantId());

        Staff staff = new Staff();
        staff.setName(request.getName());
        staff.setEmail(request.getEmail());
        staff.setTenant(tenant);
        staff.setRole(StaffRole.valueOf(request.getRole()).name());
        staff.setPassword(passwordEncoder.encode(request.getPassword()));
        staffRepository.save(staff);
    }
}
