package com.skiply.receipt.controller;

import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.service.ReceiptService;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ReceiptController {

    @Autowired
    private ReceiptService transactionService;
    
    @Operation(summary = "New Fee Receipt")
    @PostMapping(("/api/v1/receipts/transactions"))
    public ResponseEntity<Receipt> createTransaction(@RequestBody Receipt transaction) {
    	Receipt savedTransaction = transactionService.addTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }
    
    @GetMapping("/api/v1/receipts/students/{studentId}")
    public Mono<ResponseEntity<Student_Receipt_Dto>> getReceipt(@PathVariable Integer studentId) {
        return transactionService.getReceiptByStudentId(studentId)
                .map(receiptDto -> ResponseEntity.ok(receiptDto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

