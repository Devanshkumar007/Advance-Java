package com.lpu.BankingLoanApprovalSystem;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Lazy
public class AuditService {
	
	@PostConstruct
	void init() {
		System.out.println("Audit Service Started");
	}
	
	@PreDestroy
	void destroy() {
		System.out.println("Audit Service Destroyed");
	}
}

