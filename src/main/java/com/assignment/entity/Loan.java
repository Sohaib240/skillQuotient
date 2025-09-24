package com.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(nullable = false)
    private Instant borrowedAt;
    private Instant returnedAt;
}