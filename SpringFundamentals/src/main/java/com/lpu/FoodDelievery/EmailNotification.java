package com.lpu.FoodDelievery;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotification implements NotificationService{

	
	
	public EmailNotification() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("EMAIL NOTIFICATION ");
	}

	@Override
	public void sendNotification(String message) {
		System.out.println("NEW EMAIL NOTIFICATION");
		System.out.println(message);
	}
	
}
