package com.ezen.spg16.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spg16.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	@RequestMapping(value = "/")
	public String index() {
		return "loginForm";
	}
}
