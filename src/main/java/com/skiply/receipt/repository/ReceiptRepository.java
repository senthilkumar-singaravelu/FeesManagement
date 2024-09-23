package com.skiply.receipt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skiply.receipt.entity.Receipt;
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
	    // Additional query methods can be defined here if needed
	    List<Receipt> findByStudentId(int studentId);

	}


