package com.lib.library.impl.repository;

import com.lib.library.db.entity.BookLoan;
import java.util.List;
import java.util.Optional;

public interface BookLoanRepository {
    BookLoan save(BookLoan bookLoan);
    List<BookLoan> findByReaderId(Long readerId);
    Optional<BookLoan> findById(Long id);
    List<BookLoan> findAll();
    BookLoan update(BookLoan bookLoan);
    void deleteById(Long id);
}