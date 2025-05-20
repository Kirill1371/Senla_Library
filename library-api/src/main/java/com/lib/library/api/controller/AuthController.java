package com.lib.library.api.controller;

import com.lib.library.api.dto.AuthRequestDto;
import com.lib.library.api.dto.AuthResponseDto;
import com.lib.library.api.dto.RegisterRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request);

    @PostMapping("/register")
    ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDto request);
}