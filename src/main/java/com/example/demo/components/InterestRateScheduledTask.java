package com.example.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.InterestAccountService;

@Component
public class InterestRateScheduledTask {
	
	@Autowired
	private InterestAccountService interestAccountService;


	//@Scheduled(cron = "0/20 * * * * ?")// for testing
	@Scheduled(cron="0 10 23 L * ?")
	public void addInterestRate() {
		System.out.println("adding Interest Task running");
		
		interestAccountService.calculateAndUpdateBalance();

	}
}
