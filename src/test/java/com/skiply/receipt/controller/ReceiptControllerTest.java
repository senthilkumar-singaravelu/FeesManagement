package com.skiply.receipt.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Autowired
    private ObjectMapper objectMapper;

    private Receipt receipt;
    private Student_Receipt_Dto studentReceiptDto;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
        receipt.setTransactionId(1);
        receipt.setStudentId(1001);
        receipt.setAmount(BigDecimal.valueOf(200.0));  // Using BigDecimal.valueOf for amount
        receipt.setStatus("Completed");

        studentReceiptDto = new Student_Receipt_Dto();
        studentReceiptDto.setStudentId(1001);
        studentReceiptDto.setStudentName("John Doe");
        studentReceiptDto.setGrade("A");
        studentReceiptDto.setAmount(BigDecimal.valueOf(200.0));  // Also using BigDecimal here
    }

  @Test
    void testCreateTransaction() throws Exception {
        when(receiptService.addTransaction(any(Receipt.class))).thenReturn(receipt);

        mockMvc.perform(post("/api/v1/receipts/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(receipt)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1))
                .andExpect(jsonPath("$.studentId").value(1001))
                .andExpect(jsonPath("$.amount").value(200.0));  // JSON will represent BigDecimal as Double
    }

   @Test
    void testGetReceiptByStudentId() throws Exception {
        when(receiptService.getReceiptByStudentId(1001)).thenReturn(studentReceiptDto);

        mockMvc.perform(get("/api/v1/receipts/students/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(1001))
                .andExpect(jsonPath("$.studentName").value("John Doe"))
                .andExpect(jsonPath("$.amount").value(200.0));  // Compare with Double since JSON uses Double
    }
}
