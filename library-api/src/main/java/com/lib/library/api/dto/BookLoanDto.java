package com.lib.library.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookLoanDto {
    private Long id;
    private Long readerId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}
