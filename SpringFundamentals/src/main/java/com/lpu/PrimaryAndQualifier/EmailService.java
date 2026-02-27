package com.lpu.PrimaryAndQualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements NotificationService{

	@Override
	public void sendNotification() {
		// TODO Auto-generated method stub
		System.out.println("EMAIL SERVICE");
	}

}
