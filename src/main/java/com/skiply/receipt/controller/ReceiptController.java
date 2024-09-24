package com.skiply.receipt.controller;

import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.service.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/receipts")
public class ReceiptController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @Autowired
    private ReceiptService receiptService;

    @Operation(summary = "Create a new Fee Receipt")
    @PostMapping("/transactions")
    public ResponseEntity<Receipt> createTransaction(@RequestBody Receipt transaction) {
        logger.info("Creating transaction for student ID: {}", transaction.getStudentId());
        
        Receipt savedTransaction = receiptService.addTransaction(transaction);
        
        logger.info("Transaction created successfully with ID: {}", savedTransaction.getTransactionId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @Operation(summary = "Get Receipt by Student ID")
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student_Receipt_Dto> getReceipt(@PathVariable Integer studentId) {
        logger.info("Fetching receipt for student ID: {}", studentId);
        
        Student_Receipt_Dto receiptDto = receiptService.getReceiptByStudentId(studentId);
        
        logger.info("Receipt fetched successfully for student ID: {}", studentId);
        return ResponseEntity.ok(receiptDto);
    }
}
