package com.lib.library.impl.controller;

import com.lib.library.api.controller.ReaderController;
import com.lib.library.api.dto.*;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.service.BookLoanService;
import com.lib.library.impl.service.ReaderAuthService;
import com.lib.library.impl.service.ReaderService;
import com.lib.library.impl.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReaderControllerImpl implements ReaderController {

    private final ReaderService service;
    private final ReaderAuthService authService;

    private final BookLoanService bookLoanService;
    private final HttpServletRequest request;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public ReaderAuthResponse login(ReaderAuthRequest request) {
        return authService.authenticate(request);
    }

    @Override
    public ResponseEntity<List<BookLoanDto>> getOwnBookLoans() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Reader reader = userDetailsService.getReaderByName(username);
        List<BookLoanDto> loans = bookLoanService.findByReaderId(reader.getId());
        return ResponseEntity.ok(loans);
    }

    @Override
    public ReaderDto create(ReaderDto dto) {
        return service.create(dto);
    }

    @Override
    public ReaderDto getById(Long id) {
        return service.getById(id);
    }

    @Override
    public List<ReaderDto> getAll() {
        return service.getAll();
    }

    @Override
    public ReaderDto update(Long id, ReaderDto dto) {
        return service.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}

