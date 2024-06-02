package com.assignment.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryDto {

    private Long id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
