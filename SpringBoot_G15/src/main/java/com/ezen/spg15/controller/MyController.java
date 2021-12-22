package com.ezen.spg15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg15.dao.ITransactionDao3;
import com.ezen.spg15.service.MyService;

@Controller
public class MyController {
	@Autowired
	MyService ms; 
	
	@Autowried
	ITransactionDao3 td3;
	
	@RequestMapping(value="/")
	public String root() {
		return "buy_ticket";
	}
	
	@RequestMapping(value="/buyTicketCard")
	public String buy_ticket_card(@RequestParam("id") String id, 
			@RequestParam("amount") int amount, @RequestParam("error") String error, Model model) {
		
		int result = ms.buy(id, amount, error);
		
		if(error.equals("2")) {
			int n = 10 / 0;
		}
		td3.buy(id, Integer.parseInt(amount));
		
		return "";
	}
}
