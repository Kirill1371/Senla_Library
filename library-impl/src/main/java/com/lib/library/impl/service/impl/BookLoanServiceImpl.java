package com.lib.library.impl.service.impl;

import com.lib.library.api.dto.BookDto;
import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.db.entity.BookLoan;
import com.lib.library.impl.mapper.BookLoanMapper;
import com.lib.library.impl.repository.BookLoanRepository;
import com.lib.library.impl.service.BookLoanService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Valid
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {
    private final BookLoanRepository repository;
    private final BookLoanMapper mapper;
    private final BookServiceImpl bookService;

    @Override
    public BookLoanDto create(BookLoanDto dto) {
        BookLoan bookLoan = mapper.toEntity(dto);
        BookLoan savedLoan = repository.save(bookLoan);

        BookDto bookDto = bookService.findById(dto.getBookId());
        bookDto.setAvailable(false);
        bookService.update(bookDto.getId(), bookDto);

        return mapper.toDto(savedLoan); // <-- исправлено
    }

    @Override
    public List<BookLoanDto> findByReaderId(Long readerId) {
        return repository.findByReaderId(readerId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
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
        BookLoan existingLoan = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book loan not found: " + id));

        // Обновляем данные
        mapper.updateBookLoanFromDto(dto, existingLoan);
        existingLoan.setReturnDate(dto.getReturnDate()); // важно — даже если null

        BookLoan updatedLoan = repository.update(existingLoan);

        // Если есть returnDate — книга вернулась, делаем available = true
        if (dto.getReturnDate() != null) {
            BookDto bookDto = bookService.findById(existingLoan.getBook().getId());
            bookDto.setAvailable(true);
            bookService.update(bookDto.getId(), bookDto);
        }

        return mapper.toDto(updatedLoan);
    }


    @Override
    public void delete(Long id) {
        BookLoan loan = repository.findById(id).orElseThrow();
        if (loan.getReturnDate() == null) {
            BookDto bookDto = bookService.findById(loan.getBook().getId());
            bookDto.setAvailable(true);
            bookService.update(bookDto.getId(), bookDto);
        }
        repository.deleteById(id);
    }
}
