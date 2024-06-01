package com.assignment.library.controller;

import com.assignment.library.dto.CreateBookRequest;
import com.assignment.library.dto.GetBookResponse;
import com.assignment.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @PostMapping
    public ResponseEntity<CreateBookRequest> createBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        var savedBookDto = bookService.createBook(createBookRequest);
        URI location = URI.create(String.format("/books/%s", savedBookDto.getId()));
        return ResponseEntity.created(location).body(savedBookDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBookResponse> getBookById(@PathVariable("id") long id){
        var bookDto = bookService.getBookById(id);
        if (bookDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(GetBookResponse.builder().book(bookDto).build());
    }
}
