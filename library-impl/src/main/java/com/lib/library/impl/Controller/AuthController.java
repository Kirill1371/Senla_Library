package com.lib.library.impl.Controller;

import com.lib.library.api.dto.AuthRequestDto;
import com.lib.library.api.dto.AuthResponseDto;
import com.lib.library.api.dto.RegisterRequestDto;
import com.lib.library.impl.Service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequestDto request) {
        authenticationService.register(request);
        return ResponseEntity.ok().build();
    }
}
