package com.sbs.spring1012;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
//@ComponentScan(basePackages = "com.sbs.spring1012")
@SpringBootApplication
public class Spring1012Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring1012Application.class, args);
	}

}
