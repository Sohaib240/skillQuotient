package com.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    @NotBlank
    private String isbn;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private boolean borrowed;
}
