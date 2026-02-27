package com.lpu.paymentQ;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig4.class);
		
		TransactionLogger tx = context.getBean(TransactionLogger.class);
		PaymentProcessor pp = context.getBean(PaymentProcessor.class);
		
		tx.paymentService.processPayment(1000);
		pp.payment.processPayment(1000);
		
		context.close();
	}
}
