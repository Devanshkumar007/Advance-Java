package com.lpu.paymentQ;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpiPayment implements PaymentService{

	@Override
	public boolean processPayment(double amount) {
		System.out.println("UPI Payment ");
		return true;
	}
	
	
}
