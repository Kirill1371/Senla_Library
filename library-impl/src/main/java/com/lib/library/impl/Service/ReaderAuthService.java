package com.lib.library.impl.Service;

import com.lib.library.api.dto.ReaderAuthRequestDto;
import com.lib.library.api.dto.ReaderAuthResponseDto;

public interface ReaderAuthService {
    ReaderAuthResponseDto authenticate(ReaderAuthRequestDto request);
}