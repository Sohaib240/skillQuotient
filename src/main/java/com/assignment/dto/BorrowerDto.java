package com.assignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowerDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
}
