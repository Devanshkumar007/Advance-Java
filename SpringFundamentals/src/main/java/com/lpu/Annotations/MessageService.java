package com.lpu.Annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
	
	//Field Injection
	//	@Autowired
	
	private EmailService emailService;
	private SmsService smsService;
		
	@Autowired
	public MessageService(EmailService em, SmsService sms) {
		emailService = em ;
		smsService = sms ;
	}
	
	
	
	

	public MessageService() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void sendMessage() {
		System.out.println("Sending message: ");
		emailService.send();
		smsService.sms();
	}
}
