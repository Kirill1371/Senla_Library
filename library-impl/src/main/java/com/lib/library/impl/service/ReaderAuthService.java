package com.lib.library.impl.service;

import com.lib.library.api.dto.ReaderAuthRequest;
import com.lib.library.api.dto.ReaderAuthResponse;

public interface ReaderAuthService {
    ReaderAuthResponse authenticate(ReaderAuthRequest request);
}
