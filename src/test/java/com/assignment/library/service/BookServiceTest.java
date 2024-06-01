package com.assignment.library.service;

import com.assignment.library.dto.BookDto;
import com.assignment.library.dto.CreateBookRequest;
import com.assignment.library.entity.Book;
import com.assignment.library.entity.Rack;
import com.assignment.library.mapper.BookMapper;
import com.assignment.library.repository.BookRepository;
import com.assignment.library.repository.RackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private RackRepository rackRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook_Valid() {
        CreateBookRequest createBookRequest = CreateBookRequest.builder()
                .rackId(1L)
                .bookName("Valid Book")
                .bookDescription("Valid Description")
                .build();

        Rack rack = new Rack();
        rack.setId(1L);

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setRack(rack);
        savedBook.setName("Valid Book");
        savedBook.setDescription("Valid Description");

        when(rackRepository.findById(1L)).thenReturn(Optional.of(rack));
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        CreateBookRequest result = bookService.createBook(createBookRequest);

        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
        assertEquals("Valid Book", result.getBookName());
        assertEquals("Valid Description", result.getBookDescription());

        verify(rackRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testCreateBook_RackNotFound() {
        CreateBookRequest createBookRequest = CreateBookRequest.builder()
                .rackId(1L)
                .bookName("Valid Book")
                .bookDescription("Valid Description")
                .build();

        when(rackRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.createBook(createBookRequest);
        });

        assertEquals("Rack not found", exception.getMessage());

        verify(rackRepository, times(1)).findById(1L);
        verify(bookRepository, times(0)).save(any(Book.class));
    }

    @Test
    public void testGetBookById_ExistingId() {
        Rack rack = new Rack();
        rack.setId(1L);

        Book book = new Book();
        book.setId(1L);
        book.setRack(rack);
        book.setName("Book A");
        book.setDescription("Description A");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Book A", result.getBookName());
        assertEquals("Description A", result.getBookDescription());
        assertNotNull(result.getRack());
        assertEquals(1L, result.getRack().getId());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBookById_NonExistingId() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        BookDto result = bookService.getBookById(1L);

        assertNull(result);

        verify(bookRepository, times(1)).findById(1L);
    }
}
