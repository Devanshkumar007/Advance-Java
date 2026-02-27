package com.lpu.Annotations;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	public void send() {
		System.out.println("Through Email");
	}
}
