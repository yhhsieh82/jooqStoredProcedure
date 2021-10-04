package com.example.jooqSp;

import java.util.Objects;
import java.util.Optional;

import com.example.jooqSp.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JooqStoredProcedureApplication {
//public class JooqStoredProcedureApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(JooqStoredProcedureApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JooqStoredProcedureApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("logger print");
//		companyService.createCompany();
//	}
}
