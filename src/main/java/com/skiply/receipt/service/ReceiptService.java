package com.skiply.receipt.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skiply.receipt.config.StudentManagementFeignClient;
import com.skiply.receipt.entity.Receipt;
import com.skiply.receipt.entity.Student_Receipt_Dto;
import com.skiply.receipt.exception.ResourceNotFoundException;
import com.skiply.receipt.repository.ReceiptRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Service
public class ReceiptService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptService.class);

    @Autowired
    private ReceiptRepository transactionRepository;

    @Autowired
    private StudentManagementFeignClient studentManagementFeignClient;  // Inject Feign Client

    @CircuitBreaker(name = "studentService", fallbackMethod = "studentServiceFallback")
    public Receipt addTransaction(Receipt transaction) {
        // Call Student Management Service using Feign Client
        logger.info("Calling Student Management Service for student ID: {}", transaction.getStudentId());

        Student_Receipt_Dto student = studentManagementFeignClient.getStudentById(transaction.getStudentId());

        if (student != null) {
            logger.info("Student found: {}", student.getStudentId());
            // Student exists, save the transaction
            return transactionRepository.save(transaction);
        } else {
            logger.error("Student not found with ID: {}", transaction.getStudentId());
            throw new ResourceNotFoundException("Student not found with ID: " + transaction.getStudentId());
        }
    }

    @CircuitBreaker(name = "studentService", fallbackMethod = "studentServiceFallbackForGetReceipt")
    public Student_Receipt_Dto getReceiptByStudentId(int studentId) {
        logger.info("Fetching student details for student ID: {}", studentId);

        // Fetch student details using Feign Client
        Student_Receipt_Dto student = studentManagementFeignClient.getStudentById(studentId);

        // Fetch transaction details for the student
        List<Receipt> transactions = transactionRepository.findByStudentId(studentId);


        if (!transactions.isEmpty()) {
            Receipt latestTransaction = transactions.get(transactions.size() - 1);

            Student_Receipt_Dto receipt = new Student_Receipt_Dto();
            receipt.setStudentId(student.getStudentId());
            receipt.setStudentName(student.getStudentName());
            receipt.setGrade(student.getGrade());
            receipt.setTransactionId(latestTransaction.getTransactionId());
            receipt.setAmount(latestTransaction.getAmount());
            receipt.setTransactionDate(latestTransaction.getTransactionDate().toString());
            receipt.setCardType(latestTransaction.getCardType());
            receipt.setReferenceNumber(latestTransaction.getReferenceNumber());
            receipt.setStatus(latestTransaction.getStatus());
            receipt.setSchoolName(student.getSchoolName());

            logger.info("Returning receipt for student ID: {}", studentId);
            return receipt;
        } else {
            logger.warn("No transactions found for student ID: {}", studentId);
            throw new ResourceNotFoundException("No transactions found for student ID: " + studentId);
        }
    }

    // Fallback methods
    public Receipt studentServiceFallback(Receipt transaction, Throwable t) {
        logger.error("Student service is unavailable: {}. Cause: {}", transaction.getStudentId(), t.getMessage());
        throw new ResourceNotFoundException("Student is unavailable for ID: " + transaction.getStudentId());
    }

    public Student_Receipt_Dto studentServiceFallbackForGetReceipt(int studentId, Throwable t) {
        logger.error("Student service is unavailable for student ID: {}. Cause: {}", studentId, t.getMessage());
        throw new ResourceNotFoundException("Student  is unavailable. Please try again later.");
    }
}
