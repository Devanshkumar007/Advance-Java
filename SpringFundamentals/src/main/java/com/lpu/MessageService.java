package com.lpu;

public class MessageService {
	
	private EmailService emailService;
		
	
	public MessageService() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public MessageService(EmailService emailService) {
		super();
		this.emailService = emailService;
	}
	



	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void sendMessage() {
		System.out.println("Message Service");
		emailService.send();
	}
}
