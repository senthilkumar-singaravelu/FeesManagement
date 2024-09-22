package com.skiply.receipt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ReceiptController receiptController;

    @Mock
    private ReceiptService receiptService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(receiptController).build();
    }

   
    public void testCreateTransaction() throws Exception {
        // Create a Receipt object for testing
        Receipt receipt = new Receipt();
        receipt.setStudentId(1);
        receipt.setAmount(new BigDecimal(1000));
        receipt.setStatus("completed");
        receipt.setCardType("Credit Card");

        // Mock the addTransaction method to return the same Receipt
        when(receiptService.addTransaction(any(Receipt.class))).thenReturn(receipt);

        // Perform the POST request with the Receipt object
        mockMvc.perform(post("/api/v1/receipts/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(receipt)))
                .andExpect(status().isOk());
    }


   
    public void testGetReceipt() throws Exception {
        Integer studentId = 1;
        Student_Receipt_Dto receiptDto = new Student_Receipt_Dto();
        receiptDto.setStudentId(studentId);

        // Mock the getReceiptByStudentId method to return the Receipt DTO
        when(receiptService.getReceiptByStudentId(studentId)).thenReturn(Mono.just(receiptDto));

        // Perform the GET request to retrieve the receipt using the correct endpoint
        mockMvc.perform(get("/api/v1/receipts/" + studentId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}
