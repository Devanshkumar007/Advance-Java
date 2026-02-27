package com.lpu.PrimaryAndQualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationsForQualifiers.class);
		
//		Utils util = context.getBean(Utils.class);
		
		NotificationService email = context.getBean(EmailService.class);
		NotificationService sms = context.getBean(SmsService.class);
		email.sendNotification();
		sms.sendNotification();
		
//		util.email();
//		util.sms();
	}
}
