package com.assignment.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {

    private Long id;

    @NotNull(message = "Rack ID cannot be null")
    private Long rackId;

    @NotBlank(message = "Name cannot be blank")
    private String bookName;

    private String bookDescription;

}
