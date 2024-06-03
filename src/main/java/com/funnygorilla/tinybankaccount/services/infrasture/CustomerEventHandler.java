package com.funnygorilla.tinybankaccount.services.infrasture;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventHandler {
	
	@KafkaListener (topics = "${kafka.topic}")
	public int receive (String customerEventStr) {
		int result = -1;
		
		System.out.println(customerEventStr);
		
		return result;
	}

}
