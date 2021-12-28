package com.ezen.spm01.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(Model model) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("newProductList", ps.getNewList());
		mav.addObject("bestProductList", ps.getBestList());
		mav.setViewName("index");
		return mav;
	}
}
