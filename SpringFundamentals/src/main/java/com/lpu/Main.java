package com.lpu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		MessageService ms = context.getBean(MessageService.class);
			ms.sendMessage();
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
