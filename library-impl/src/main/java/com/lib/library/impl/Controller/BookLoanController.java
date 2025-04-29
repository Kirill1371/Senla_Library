package com.lib.library.impl.Controller;

import com.lib.library.api.dto.BookLoanDto;
import com.lib.library.impl.Service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-loans")
@RequiredArgsConstructor
public class BookLoanController {

    private final BookLoanService service;

//    public BookLoanController(BookLoanService service) {
//        this.service = service;
//    }
    @PostMapping
    public BookLoanDto create(@RequestBody BookLoanDto dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public BookLoanDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<BookLoanDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public BookLoanDto update(@PathVariable Long id, @RequestBody BookLoanDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
