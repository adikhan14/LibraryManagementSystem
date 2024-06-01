package com.assignment.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RackDto {

    private long id;
    private LibraryDto library;
    private int rowNumber;
    private int columnNumber;

}
