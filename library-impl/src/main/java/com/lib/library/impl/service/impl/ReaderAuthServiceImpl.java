package com.lib.library.impl.service.impl;

import com.lib.library.api.dto.ReaderAuthRequest;
import com.lib.library.api.dto.ReaderAuthResponse;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.repository.ReaderRepository;
import com.lib.library.impl.service.ReaderAuthService;
import com.lib.library.impl.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderAuthServiceImpl implements ReaderAuthService {

    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtTokenProvider;

    @Override
    public ReaderAuthResponse authenticate(ReaderAuthRequest request) {
        Reader reader = readerRepository.findByName(request.getName())
                .orElseThrow(() -> new BadCredentialsException("Reader not found"));

        if (!passwordEncoder.matches(request.getPassword(), reader.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(request.getName());
        return new ReaderAuthResponse(token);
    }
}
