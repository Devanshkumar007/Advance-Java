package com.lpu.PrimaryAndQualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Utils {
	
	@Autowired
	@Qualifier("emailService")
	NotificationService email;

	@Autowired	
	@Qualifier("smsService")
	NotificationService sms;
	
	public void email() {
		email.sendNotification();
	}
	
	public void sms() {
		sms.sendNotification();
	}
	
}
