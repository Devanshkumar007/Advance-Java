package com.lpu.FoodDelievery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestrauntService {
	DeliveryService deliveryService ;

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	@Autowired
	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	

	
	
	
	
	
}
