package com.lib.library.impl.repository.impl;

import com.lib.library.db.entity.Book;
import com.lib.library.db.entity.BookLoan;
import com.lib.library.db.entity.Reader;
import com.lib.library.impl.repository.BookLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookLoanRepositoryImpl implements BookLoanRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<BookLoan> bookLoanRowMapper = (rs, rowNum) -> {
        BookLoan loan = new BookLoan();
        loan.setId(rs.getLong("id"));

        Reader reader = new Reader();
        reader.setId(rs.getLong("reader_id"));
        loan.setReader(reader);

        Book book = new Book();
        book.setId(rs.getLong("book_id"));
        loan.setBook(book);

        loan.setLoanDate(rs.getObject("loan_date", LocalDate.class));
        loan.setDueDate(rs.getObject("due_date", LocalDate.class));
        loan.setReturnDate(rs.getObject("return_date", LocalDate.class));

        return loan;
    };

    @Override
    public List<BookLoan> findByReaderId(Long readerId) {
        String sql = "SELECT * FROM book_loans WHERE reader_id = ?";
        return jdbcTemplate.query(sql, new Object[]{readerId}, bookLoanRowMapper);
    }


    @Override
    public BookLoan save(BookLoan bookLoan) {
        String sql = """
            INSERT INTO book_loans (reader_id, book_id, loan_date, due_date, return_date)
            VALUES (?, ?, ?, ?, ?) RETURNING id
        """;
        Long id = jdbcTemplate.queryForObject(sql, Long.class,
                bookLoan.getReader().getId(),
                bookLoan.getBook().getId(),
                bookLoan.getLoanDate(),
                bookLoan.getDueDate(),
                bookLoan.getReturnDate()
        );
        bookLoan.setId(id);
        return bookLoan;
    }

    @Override
    public Optional<BookLoan> findById(Long id) {
        String sql = "SELECT * FROM book_loans WHERE id = ?";
        List<BookLoan> result = jdbcTemplate.query(sql, bookLoanRowMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public List<BookLoan> findAll() {
        String sql = "SELECT * FROM book_loans";
        return jdbcTemplate.query(sql, bookLoanRowMapper);
    }

    @Override
    public BookLoan update(BookLoan bookLoan) {
        String sql = """
            UPDATE book_loans
            SET reader_id = ?, book_id = ?, loan_date = ?, due_date = ?, return_date = ?
            WHERE id = ?
        """;
        jdbcTemplate.update(sql,
                bookLoan.getReader().getId(),
                bookLoan.getBook().getId(),
                bookLoan.getLoanDate(),
                bookLoan.getDueDate(),
                bookLoan.getReturnDate(),
                bookLoan.getId()
        );
        return bookLoan;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM book_loans WHERE id = ?", id);
    }
}
