package com.lpu.paymentQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

	PaymentService payment;
	
	@Autowired
	TransactionLogger tx;
	
	@Autowired
	public PaymentProcessor(@Qualifier("upiPayment") PaymentService ps) {
		this.payment=ps;
	}
}
