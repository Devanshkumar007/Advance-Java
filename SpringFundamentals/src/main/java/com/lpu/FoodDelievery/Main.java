package com.lpu.FoodDelievery;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FoodAppConfig.class);
		
		OrderService order = context.getBean(OrderService.class);
		RestrauntService restraunt = context.getBean(RestrauntService.class);
		
		order.notificationService.sendNotification("HELLO HI");
		
		context.close();
	}
}
