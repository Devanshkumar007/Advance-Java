package com.lpu.FoodDelievery;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class SmsNotification implements NotificationService {
	
	public SmsNotification() {
		System.out.println("SMS NOTIFICATION");
	}
	
	
	@Override
	public void sendNotification(String message) {
		// TODO Auto-generated method stub
		System.out.println("NEW SMS NOTIFICATION");
		System.out.println(message);
	}

}
