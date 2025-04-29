package com.lib.library.impl.Service.impl;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.db.entity.BookLoan;
import com.lib.library.impl.mapper.BookLoanMapper;
import com.lib.library.impl.Repository.BookLoanRepository;
import com.lib.library.impl.Service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository repository;
    private final BookLoanMapper mapper;

    @Override
    public BookLoanDto create(BookLoanDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public BookLoanDto getById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<BookLoanDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookLoanDto update(Long id, BookLoanDto dto) {
        BookLoan entity = repository.findById(id).orElseThrow();
        entity.setLoanDate(dto.getLoanDate());
        entity.setDueDate(dto.getDueDate());
        entity.setReturnDate(dto.getReturnDate());
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
