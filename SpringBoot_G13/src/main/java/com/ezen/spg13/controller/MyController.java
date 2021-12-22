package com.ezen.spg13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.spg13.service.MyService;

@Controller
public class MyController {
	@Autowired
	MyService ms; 
}
