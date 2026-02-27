package com.lpu.paymentQ;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class CreditCard implements PaymentService {
	
	@Override
	public boolean processPayment(double amount) {
		// TODO Auto-generated method stub
		System.out.println("CREDIT CARD payment");
		return true;
	}
}