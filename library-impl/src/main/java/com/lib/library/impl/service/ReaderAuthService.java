package com.lib.library.impl.service;

import com.lib.library.api.dto.ReaderAuthRequestDto;
import com.lib.library.api.dto.ReaderAuthResponseDto;

public interface ReaderAuthService {
    ReaderAuthResponseDto authenticate(ReaderAuthRequestDto request);
}