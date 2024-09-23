package com.skiply.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ReceiptManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptManagementApplication.class, args);
	}

}
