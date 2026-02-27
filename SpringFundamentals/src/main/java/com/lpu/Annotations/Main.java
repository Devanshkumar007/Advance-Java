package com.lpu.Annotations;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MessageService ms = context.getBean(MessageService.class);
		ms.sendMessage();
		context.close();
		
	}
}
