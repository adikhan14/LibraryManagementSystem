package com.assignment.library.controller;

import com.assignment.library.dto.BookDto;
import com.assignment.library.dto.CreateBookRequest;
import com.assignment.library.dto.GetBookResponse;
import com.assignment.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateBook_Valid() throws Exception {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setRackId(1L);
        createBookRequest.setBookName("Valid Book");
        createBookRequest.setBookDescription("Valid Description");

        CreateBookRequest savedBookRequest = new CreateBookRequest();
        savedBookRequest.setId(1L);
        savedBookRequest.setRackId(1L);
        savedBookRequest.setBookName("Valid Book");
        savedBookRequest.setBookDescription("Valid Description");

        when(bookService.createBook(any(CreateBookRequest.class))).thenReturn(savedBookRequest);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/1"))
                .andExpect(content().json(objectMapper.writeValueAsString(savedBookRequest)));

        verify(bookService, times(1)).createBook(any(CreateBookRequest.class));
    }

    @Test
    public void testCreateBook_Invalid_NoName() throws Exception {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setRackId(1L);
        createBookRequest.setBookDescription("Valid Description");

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookRequest)))
                .andExpect(status().isBadRequest());

        verify(bookService, times(0)).createBook(any(CreateBookRequest.class));
    }

    @Test
    public void testCreateBook_Invalid_NoRackId() throws Exception {
        CreateBookRequest createBookRequest = new CreateBookRequest();
        createBookRequest.setBookName("Valid Book");
        createBookRequest.setBookDescription("Valid Description");

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBookRequest)))
                .andExpect(status().isBadRequest());

        verify(bookService, times(0)).createBook(any(CreateBookRequest.class));
    }

    @Test
    public void testGetBookById_ExistingId() throws Exception {
        BookDto bookDto = BookDto.builder()
                .id(1L)
                .bookName("Book A")
                .bookDescription("Description A")
                .build();

        when(bookService.getBookById(anyLong())).thenReturn(bookDto);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(GetBookResponse.builder().book(bookDto).build())));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testGetBookById_NonExistingId() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).getBookById(1L);
    }
}
