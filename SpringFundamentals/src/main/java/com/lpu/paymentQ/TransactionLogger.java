package com.lpu.paymentQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


@Component
public class TransactionLogger {
	
	PaymentService paymentService ;
	
	@Autowired
	public TransactionLogger(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Logger initialized");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Logger destroyed");
	}

}


