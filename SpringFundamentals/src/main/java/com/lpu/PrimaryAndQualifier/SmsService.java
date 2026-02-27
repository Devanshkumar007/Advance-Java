package com.lpu.PrimaryAndQualifier;

import org.springframework.stereotype.Component;

@Component
public class SmsService implements NotificationService{

	@Override
	public void sendNotification() {
		// TODO Auto-generated method stub
		System.out.println("SMS Service");
	}
}
