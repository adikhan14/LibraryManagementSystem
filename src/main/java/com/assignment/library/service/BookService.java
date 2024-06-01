package com.assignment.library.service;

import com.assignment.library.dto.BookDto;
import com.assignment.library.dto.CreateBookRequest;
import com.assignment.library.entity.Book;
import com.assignment.library.mapper.BookMapper;
import com.assignment.library.repository.BookRepository;
import com.assignment.library.repository.RackRepository;
import org.springframework.stereotype.Service;


import static com.assignment.library.mapper.BookMapper.toBook;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final RackRepository rackRepository;

    public BookService(BookRepository bookRepository, RackRepository rackRepository){
        this.bookRepository = bookRepository;
        this.rackRepository = rackRepository;
    }
    public CreateBookRequest createBook(CreateBookRequest createBookRequest){

        Book existingBook = null;
        if (createBookRequest.getId() != null)
            existingBook = bookRepository.findById(createBookRequest.getId()).orElse(null);
        var rack = rackRepository.findById(createBookRequest.getRackId())
                .orElseThrow(() -> new RuntimeException("Rack not found"));
        var book = toBook(createBookRequest, existingBook);
        book.setRack(rack);
        var savedBook = bookRepository.save(book);
        createBookRequest.setId(savedBook.getId());
        return createBookRequest;

    }

    public BookDto getBookById(long id) {
        var book = bookRepository.findById(id);
        return book.map(BookMapper::fromBook).orElse(null);
    }
}
