package com.skiply.receipt.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skiply.receipt.entity.Student_Receipt_Dto;

@FeignClient(name = "studentService", url = "${student.management.service.url}")
public interface StudentManagementFeignClient {

    @GetMapping("/{studentId}")
    Student_Receipt_Dto getStudentById(@PathVariable("studentId") int studentId);
}
