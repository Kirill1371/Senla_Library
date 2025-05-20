package com.lib.library.impl.controller;

import com.lib.library.api.controller.AuthController;
import com.lib.library.api.dto.AuthRequestDto;
import com.lib.library.api.dto.AuthResponseDto;
import com.lib.library.api.dto.RegisterRequestDto;
import com.lib.library.impl.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationServiceImpl authenticationService;

    @Override
    public ResponseEntity<AuthResponseDto> login(AuthRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @Override
    public ResponseEntity<Void> register(RegisterRequestDto request) {
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }
}