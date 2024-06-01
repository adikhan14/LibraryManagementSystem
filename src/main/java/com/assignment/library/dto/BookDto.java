package com.assignment.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto {

    private Long id;
    private RackDto rack;
    private String bookName;
    private String bookDescription;
}
