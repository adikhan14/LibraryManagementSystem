package com.assignment.library.mapper;

import com.assignment.library.dto.BookDto;
import com.assignment.library.dto.CreateBookRequest;
import com.assignment.library.dto.LibraryDto;
import com.assignment.library.dto.RackDto;
import com.assignment.library.entity.Book;
import com.assignment.library.entity.Library;
import com.assignment.library.entity.Rack;

public class BookMapper {

    public static Book toBook(CreateBookRequest createBookRequest, Book book){
        if (book == null)
            book = new Book();
         book.setName(createBookRequest.getBookName());
         book.setDescription(createBookRequest.getBookDescription());
         return book;
    }

    public static BookDto fromBook(Book book){
        return BookDto.builder()
                .id(book.getId())
                .rack(fromRack(book.getRack()))
                .bookName(book.getName())
                .bookDescription(book.getDescription())
                .build();
    }

    public static RackDto fromRack(Rack rack){
        if (rack == null)
            return new RackDto();
        return RackDto.builder()
                .id(rack.getId())
                .library(fromLibrary(rack.getLibrary()))
                .rowNumber(rack.getRowNumber())
                .columnNumber(rack.getColumnNumber())
                .build();
    }

    public static LibraryDto fromLibrary(Library library){
        if (library == null)
            return new LibraryDto();
        return LibraryDto.builder()
                .id(library.getId())
                .name(library.getName())
                .startTime(library.getStartTime())
                .endTime(library.getEndTime())
                .build();
    }
}
