package com.skiply.receipt.controller;

import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.service.ReceiptService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;
    
    @Operation(summary = "New Fee Receipt")
    @PostMapping("/api/v1/receipts/transactions")
    public ResponseEntity<Receipt> createTransaction(@RequestBody Receipt transaction) {
        Receipt savedTransaction = receiptService.addTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }
    
    @Operation(summary = "Get Receipt by Student ID")
    @GetMapping("/api/v1/receipts/students/{studentId}")
    public ResponseEntity<Student_Receipt_Dto> getReceipt(@PathVariable Integer studentId) {
    
            Student_Receipt_Dto receiptDto = receiptService.getReceiptByStudentId(studentId);
            return ResponseEntity.ok(receiptDto);
        
    }
}
