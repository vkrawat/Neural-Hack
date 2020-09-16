package com.bankmng.chargem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.bankmng.chargem.BankController","com.bankmng.chargem.BankService"})
@EnableJpaRepositories(basePackages = "com.bankmng.chargem.BankRepository")
@EntityScan(basePackages = "com.bankmng.chargem.Entity")
@EnableAutoConfiguration

public class ChargemApplication {

	public static void main(String[] args) {
		System.out.println("start..");
		SpringApplication.run(ChargemApplication.class, args);
		System.out.println(" done..");
	}

}
