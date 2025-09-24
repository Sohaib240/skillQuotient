package com.assignment.service.impl;

import com.assignment.dto.BorrowerDto;
import com.assignment.entity.Borrower;
import com.assignment.exception.ConflictException;
import com.assignment.repository.BorrowerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowerServiceImplTest {

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    @Mock
    private BorrowerRepository borrowerRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBorrower_Success() {
        BorrowerDto dto = new BorrowerDto(null, "Sohaib", "sohaib@aslam.com");
        Borrower borrowerEntity = new Borrower(1L, "Sohaib", "sohaib@aslam.com");
        BorrowerDto expectedDto = new BorrowerDto(1L, "Sohaib", "sohaib@aslam.com");

        when(borrowerRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(modelMapper.map(dto, Borrower.class)).thenReturn(borrowerEntity);
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrowerEntity);
        when(modelMapper.map(borrowerEntity, BorrowerDto.class)).thenReturn(expectedDto);

        BorrowerDto result = borrowerService.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Sohaib", result.getName());
        assertEquals("sohaib@aslam.com", result.getEmail());

        verify(borrowerRepository, times(1)).findByEmail(dto.getEmail());
    }

    @Test
    void createBorrower_EmailAlreadyExists_ThrowsConflictException() {
        BorrowerDto dto = new BorrowerDto(null, "Sohaib", "sohaib@aslam.com");
        Borrower existingBorrower = new Borrower(2L, "Sohaib", "sohaib@aslam.com");

        when(borrowerRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(existingBorrower));

        ConflictException exception = assertThrows(ConflictException.class,
                () -> borrowerService.create(dto));

        assertEquals("Email already exists", exception.getMessage());

        verify(borrowerRepository, times(1)).findByEmail(dto.getEmail());
        verify(borrowerRepository, never()).save(any(Borrower.class));
        verify(modelMapper, never()).map(any(), any());
    }
}