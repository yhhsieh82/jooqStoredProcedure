package com.example.jooqSp.controller;

import java.time.Instant;

import com.example.jooqSp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@PostMapping
	public void createCompany(@RequestParam(name = "name") String name,
							  @RequestParam(name = "description") String description,
							  @RequestParam(name = "industry") String industry) throws InterruptedException {
		long l = Instant.now().toEpochMilli();
		companyService.createCompany();
	}
}
