package com.ezen.spg16.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.spg16.dto.MemberVO;
import com.ezen.spg16.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	// 로그인창이동
	@RequestMapping(value = "/")
	public String index() {
		return "loginForm";
	}
	
	// 로그인
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result, Model model, HttpServletRequest request) {
		
		// 로그인시 에러가 있을 때
		if(result.hasErrors()) { 
			// 해당 에러가 id와 pw 관련이라면 로그인 창으로 돌아간다.
			if(result.getFieldError("id") != null) {
				model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
				return "loginForm";
			}else if(result.getFieldError("pw") != null) {
				model.addAttribute("message", result.getFieldError("pw").getDefaultMessage());
				return "loginForm";
			}
		}
		
		return "main";
	}
	
}
