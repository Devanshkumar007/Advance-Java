package com.lpu.Annotations;

import org.springframework.stereotype.Component;

@Component
public class SmsService {
	public void sms() {
		System.out.println("SMS SERVICE");
	}
}
