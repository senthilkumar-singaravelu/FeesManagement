package com.skiply.receipt.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.exception.ResourceNotFoundException;
import com.skiply.receipt.repository.ReceiptRepository;

class ReceiptServiceTest {

    @InjectMocks
    private ReceiptService receiptService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ReceiptRepository receiptRepository;

    private Receipt receipt;
    private Student_Receipt_Dto studentReceiptDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample Receipt object
        receipt = new Receipt();
        receipt.setTransactionId(1);
        receipt.setStudentId(1001);
        receipt.setAmount(BigDecimal.valueOf(200.0));
        receipt.setStatus("Completed");
        receipt.setTransactionDate(LocalDateTime.now());  // Set a valid transactionDate

        // Sample Student_Receipt_Dto object
        studentReceiptDto = new Student_Receipt_Dto();
        studentReceiptDto.setStudentId(1001);
        studentReceiptDto.setStudentName("John Doe");
        studentReceiptDto.setGrade("A");
    }

    @Test
    void testAddTransaction_studentExists() {
        // Mock RestTemplate behavior for a successful student fetch
        when(restTemplate.getForObject(anyString(), eq(Student_Receipt_Dto.class)))
                .thenReturn(studentReceiptDto);

        // Mock ReceiptRepository save operation
        when(receiptRepository.save(any(Receipt.class))).thenReturn(receipt);

        // Call the method under test
        Receipt savedReceipt = receiptService.addTransaction(receipt);

        // Assert that the receipt was saved
        assertNotNull(savedReceipt);
        assertEquals(1001, savedReceipt.getStudentId());
        assertEquals(0, savedReceipt.getAmount().compareTo(BigDecimal.valueOf(200.0))); // Use compareTo for BigDecimal
    }

    @Test
    void testAddTransaction_studentDoesNotExist() {
        // Mock RestTemplate to return null (student not found)
        when(restTemplate.getForObject(anyString(), eq(Student_Receipt_Dto.class)))
                .thenReturn(null);

        // Test that an exception is thrown when the student is not found
        assertThrows(ResourceNotFoundException.class, () -> receiptService.addTransaction(receipt));
    }

    @Test
    void testGetReceiptByStudentId_studentExistsAndHasTransactions() {
        // Mock RestTemplate behavior for fetching student
        when(restTemplate.getForObject(anyString(), eq(Student_Receipt_Dto.class)))
                .thenReturn(studentReceiptDto);

        // Mock ReceiptRepository to return a list of transactions
        when(receiptRepository.findByStudentId(1001)).thenReturn(Arrays.asList(receipt));

        // Call the method under test
        Student_Receipt_Dto result = receiptService.getReceiptByStudentId(1001);

        // Assert that the receipt details are correctly returned
        assertNotNull(result);
        assertEquals(1001, result.getStudentId());
        assertEquals("John Doe", result.getStudentName());
        assertEquals(0, result.getAmount().compareTo(BigDecimal.valueOf(200.0))); // Use compareTo for BigDecimal
    }

    @Test
    void testGetReceiptByStudentId_noTransactionsFound() {
        // Mock RestTemplate to return a student
        when(restTemplate.getForObject(anyString(), eq(Student_Receipt_Dto.class)))
                .thenReturn(studentReceiptDto);

        // Mock ReceiptRepository to return an empty list of transactions
        when(receiptRepository.findByStudentId(1001)).thenReturn(Arrays.asList());

        // Test that an exception is thrown when no transactions are found
        assertThrows(ResourceNotFoundException.class, () -> receiptService.getReceiptByStudentId(1001));
    }

    @Test
    void testGetReceiptByStudentId_studentNotFound() {
        // Mock RestTemplate to return null (student not found)
        when(restTemplate.getForObject(anyString(), eq(Student_Receipt_Dto.class)))
                .thenReturn(null);

        // Test that an exception is thrown when the student is not found
        assertThrows(ResourceNotFoundException.class, () -> receiptService.getReceiptByStudentId(1001));
    }
}
