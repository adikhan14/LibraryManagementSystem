package com.assignment.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LibraryDto {

    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
