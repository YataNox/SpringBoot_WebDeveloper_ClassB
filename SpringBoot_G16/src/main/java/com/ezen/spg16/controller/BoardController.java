package com.ezen.spg16.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg16.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService bs;
	
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null)
			mav.setViewName("loginForm");
		else {
			mav.addObject("boardList", bs.selectBoardAll());
			mav.setViewName("main");
		}
		return mav;
	}
}
