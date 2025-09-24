package com.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private Long id;
    private Long borrowerId;
    private String borrowerName;
    private Long bookId;
    private String bookTitle;
    private Instant borrowedAt;
    private Instant returnedAt;
}
